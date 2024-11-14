package net.adhikary.mrtbuddy.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.ContextCompat.startActivity
import io.github.aakira.napier.Napier

class AndroidFileSaver(private val context: Context) : FileSaver {
    override suspend fun saveFile(fileName: String, mimeType: String, data: ByteArray) {
        try {
            val contentValues = ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                put(MediaStore.Downloads.MIME_TYPE, mimeType)
                put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }

            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            
            uri?.let { 
                resolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(data)
                }
                Napier.d("File saved successfully to Downloads: $fileName")
                
                // Create and start intent to open the file
                val openIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, mimeType)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                
                try {
                    context.startActivity(openIntent)
                    android.widget.Toast.makeText(
                        context,
                        "File \"$fileName\" saved to Downloads",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Napier.e("Could not open file: ${e.message}", e)
                }
            } ?: throw IllegalStateException("Failed to create new MediaStore record.")
            
        } catch (e: Exception) {
            Napier.e("Error saving file: ${e.message}", e)
            throw e
        }
    }
}
