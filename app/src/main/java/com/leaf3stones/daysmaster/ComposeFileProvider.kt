package com.leaf3stones.daysmaster

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.util.Date

class ComposeFileProvider : FileProvider(R.xml.filepaths) {
    companion object {
        fun getImageUri(context: Context): Uri {
            // 1
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            // 2
            val file = File.createTempFile(
                "${Date().time}",
                ".jpg",
                directory
            )
            // 3
            val authority = context.packageName + ".fileprovider"
            // 4
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}