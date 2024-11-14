package net.adhikary.mrtbuddy.utils

import platform.Foundation.*

class IOSFileSaver : FileSaver {
    override suspend fun saveFile(fileName: String, mimeType: String, data: ByteArray) {
        val paths = NSSearchPathForDirectoriesInDomains(
            NSSearchPathDirectory.DocumentDirectory,
            NSSearchPathDomainMask.UserDomainMask,
            true
        )
        val documentsDirectory = paths.firstOrNull() as? String ?: return
        val filePath = documentsDirectory + "/" + fileName
        val nsData = NSData.create(
            bytes = data.usePinned { it.addressOf(0) },
            length = data.size.toULong()
        )
        nsData.writeToFile(filePath, atomically = true)
    }
}
