package com.victorkirui.domain.usecases

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class SendCardsUseCase {
    operator fun invoke(context: Context, fileNames: List<String>) {
        val imageUris = fileNames.mapNotNull { fileName ->
            val file = File(getResourcesPath(context), fileName)
            if (file.exists()) {
                FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
            } else {
                null
            }
        }

        if (imageUris.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                type = "image/*"
                putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(imageUris))
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            if (context is Activity) {
                context.startActivity(Intent.createChooser(intent, "Share Images"))
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(Intent.createChooser(intent, "Share Images"))
            }
        }
    }

    private fun getResourcesPath(context: Context): String {
        return File(context.filesDir, "modules-ui/resources/images").absolutePath
    }
}
