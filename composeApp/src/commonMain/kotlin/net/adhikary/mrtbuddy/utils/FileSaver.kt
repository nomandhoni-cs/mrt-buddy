package net.adhikary.mrtbuddy.utils

interface FileSaver {
    suspend fun saveFile(fileName: String, mimeType: String, data: ByteArray)
}
