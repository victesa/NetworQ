package com.victorkirui.domain.usecases

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.domain.repositories.CardRepository
import java.io.File
import java.io.FileOutputStream
import java.util.Date

class SaveCardUseCase(
    private val context: Context,
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(
        addCardModel: AddCardModel,
        bitmap: Bitmap,
        onBitMapReady: () -> Unit,
        onImageStored: () -> Unit,
        onDetailsStored: () -> Unit
    ) {
        if (addCardModel.firstName.isEmpty()) {
            throw IllegalArgumentException("First Name is Blank")
        } else if (addCardModel.lastName.isEmpty()) {
            throw IllegalArgumentException("Last Name is Blank")
        } else {
            try {
                //To update the Progress Dialog
                onBitMapReady()

                val file = saveBitmapToResources(
                    bitmap = bitmap,
                    fileName = Date().time.toString(),
                    context = context
                )

                onImageStored()

                cardRepository.saveCard(
                    addCardModel = addCardModel.copy(cardImagePath = file.absolutePath)
                )

                onDetailsStored()
            } catch (e: Exception) {
                Log.e("SaveCardError", "Failed to save card: ${e.message}")
                throw e // Re-throw the exception to be handled by the caller
            }
        }
    }
}

fun getResourcesPath(context: Context): File {
    return File(context.filesDir, "modules-ui/resources/images").apply { mkdirs() }
}

fun saveBitmapToResources(context: Context, bitmap: Bitmap, fileName: String): File {
    val directory = getResourcesPath(context)
    val file = File(directory, "$fileName.png")

    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }

    return file
}