package net.adhikary.mrtbuddy.ui.screens.transactionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import net.adhikary.mrtbuddy.data.TransactionEntityWithAmount
import net.adhikary.mrtbuddy.repository.TransactionRepository
import net.adhikary.mrtbuddy.utils.FileSaver

class TransactionListViewModel(
    private val cardIdm: String,
    private val transactionRepository: TransactionRepository,
    private val fileSaver: FileSaver
) : ViewModel() {
    private val _state = MutableStateFlow(TransactionListState())
    val state: StateFlow<TransactionListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val transactions = transactionRepository.getTransactionsByCardIdm(cardIdm)
                _state.update { it.copy(isLoading = false, transactions = transactions) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }


    fun exportTransactions() {
        Napier.d("exportTransactions invoked")
        viewModelScope.launch {
            Napier.d("exportTransactions viewModelScope.launch post")
            try {
                val csvData = getCsvData()
                val timestamp = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val formattedTimestamp = "${timestamp.year}${
                    timestamp.monthNumber.toString().padStart(2, '0')
                }${
                    timestamp.dayOfMonth.toString().padStart(2, '0')
                }_${
                    timestamp.hour.toString().padStart(2, '0')
                }${
                    timestamp.minute.toString().padStart(2, '0')
                }${
                    timestamp.second.toString().padStart(2, '0')
                }"
                
                fileSaver.saveFile(
                    "transactions_${cardIdm}_$formattedTimestamp.csv",
                    "text/csv",
                    csvData.encodeToByteArray()
                )
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private suspend fun getCsvData(): String {
        val transactions = transactionRepository.getTransactionsByCardIdm(cardIdm)
        return convertTransactionsToCsv(transactions)
    }

    private fun convertTransactionsToCsv(transactions: List<TransactionEntityWithAmount>): String {
        val csvBuilder = StringBuilder()
        csvBuilder.append("Date,From,To,Amount\n")
        for (trx in transactions) {
            val date = formatDate(trx.transactionEntity.dateTime)
            val fromStation = trx.transactionEntity.fromStation
            val toStation = trx.transactionEntity.toStation
            val amount = trx.amount?.toString() ?: "N/A"
            csvBuilder.append("$date,$fromStation,$toStation,$amount\n")
        }
        return csvBuilder.toString()
    }

    private fun formatDate(timestamp: Long): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val year = dateTime.year
        val month = dateTime.monthNumber.toString().padStart(2, '0')
        val day = dateTime.dayOfMonth.toString().padStart(2, '0')
        val isAM = dateTime.hour < 12
        val hour12 = when (dateTime.hour) {
            0 -> "12"
            in 1..12 -> dateTime.hour.toString()
            else -> (dateTime.hour - 12).toString()
        }.padStart(2, '0')
        val minute = dateTime.minute.toString().padStart(2, '0')
        val second = dateTime.second.toString().padStart(2, '0')
        val amPm = if (isAM) "AM" else "PM"

        val date = "$year-$month-$day"
        val time = "$hour12:$minute:$second $amPm"

        return "$date $time"
    }
}
