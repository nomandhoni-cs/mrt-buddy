package net.adhikary.mrtbuddy.ui.screens.home

import MoreScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mrtbuddy.composeapp.generated.resources.Res
import mrtbuddy.composeapp.generated.resources.balance
import mrtbuddy.composeapp.generated.resources.fare
import mrtbuddy.composeapp.generated.resources.historyTab
import mrtbuddy.composeapp.generated.resources.more
import net.adhikary.mrtbuddy.navigation.Screen
import net.adhikary.mrtbuddy.ui.components.AppsIcon
import net.adhikary.mrtbuddy.ui.components.BalanceCard
import net.adhikary.mrtbuddy.ui.components.CalculatorIcon
import net.adhikary.mrtbuddy.ui.components.CardIcon
import net.adhikary.mrtbuddy.ui.components.HistoryIcon
import net.adhikary.mrtbuddy.ui.components.TransactionHistoryList
import net.adhikary.mrtbuddy.ui.screens.farecalculator.FareCalculatorScreen
import net.adhikary.mrtbuddy.ui.screens.history.HistoryScreen
import net.adhikary.mrtbuddy.ui.screens.licenses.OpenSourceLicensesScreen
import net.adhikary.mrtbuddy.ui.screens.transactionlist.TransactionListScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val navController = net.adhikary.mrtbuddy.navigation.rememberNavController()
    val hasTransactions = uiState.transaction.isNotEmpty()
    val currentScreen = navController.currentScreen

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface
            ) {
                BottomNavigationItem(
                    icon = {
                        CalculatorIcon()
                    },
                    label = { Text(stringResource(Res.string.fare)) },
                    selected = currentScreen is Screen.Calculator,
                    onClick = { navController.navigate(Screen.Calculator) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                BottomNavigationItem(
                    icon = { CardIcon() },
                    label = { Text(stringResource(Res.string.balance)) },
                    selected = currentScreen is Screen.Home,
                    onClick = { navController.navigate(Screen.Home) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                BottomNavigationItem(
                    icon = { HistoryIcon() },
                    label = { Text(stringResource(Res.string.historyTab)) },
                    selected = currentScreen is Screen.History || currentScreen is Screen.TransactionList,
                    onClick = { navController.navigate(Screen.History) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
                BottomNavigationItem(
                    icon = {
                        AppsIcon()
                    },
                    label = { Text(stringResource(Res.string.more)) },
                    selected = currentScreen is Screen.More,
                    onClick = { navController.navigate(Screen.More) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    ) { paddingValues ->
        when (navController.currentScreen) {
            Screen.Home -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BalanceCard(
                            cardState = uiState.cardState,
                            cardName = uiState.cardName,
                        )

                        if (hasTransactions) {
                            TransactionHistoryList(uiState.transactionWithAmount)
                        }
                    }
                }
            }

            Screen.Calculator -> {
                FareCalculatorScreen(
                    cardState = uiState.cardState
                )
            }

            Screen.More -> {
                MoreScreen(
                    onNavigateToLicenses = {
                        navController.navigate(Screen.OpenSourceLicenses)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }

            Screen.History -> {
                HistoryScreen(
                    onCardSelected = { cardIdm ->
                        navController.navigate(Screen.TransactionList(cardIdm))
                    }
                )
            }

            is Screen.TransactionList -> {
                TransactionListScreen(
                    cardIdm = (navController.currentScreen as Screen.TransactionList).cardIdm,
                    paddingValues = paddingValues
                )
            }

            Screen.OpenSourceLicenses -> {
                OpenSourceLicensesScreen(
                    onBack = {
                        navController.navigateBack()
                    }
                )
            }
        }
    }
}
