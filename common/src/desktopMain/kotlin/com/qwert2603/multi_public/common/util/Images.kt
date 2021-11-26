package com.qwert2603.multi_public.common.util

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image.Companion.makeFromEncoded
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

/**
 * From https://github.com/joreilly/PeopleInSpace/blob/main/compose-desktop/src/main/kotlin/main.kt
 */
@Composable
actual fun UrlImage(
    imageUrl: String?,
    placeHolder: ImageVector,
    modifier: Modifier,
) {
    val imageAsset: ImageBitmap? = imageUrl?.let { fetchImage(it) }

    if (imageAsset != null) {
        Image(
            imageAsset,
            contentDescription = null,
            modifier = modifier,
        )
    } else {
        Image(
            placeHolder,
            contentDescription = null,
            modifier = modifier,
        )
    }
}

@Composable
fun fetchImage(url: String): ImageBitmap? {
    val imageBitmap by produceState<ImageBitmap?>(
        key1 = url,
        initialValue = null,
        producer = {
            value = loadFullImage(url)
                ?.let(::toByteArray)
                ?.let(::makeFromEncoded)
                ?.toComposeImageBitmap()
        },
    )
    return imageBitmap
}

private fun toByteArray(bitmap: BufferedImage): ByteArray {
    val baos = ByteArrayOutputStream()
    ImageIO.write(bitmap, "png", baos)
    return baos.toByteArray()
}

private suspend fun loadFullImage(source: String): BufferedImage? = withContext(Dispatchers.IO) {
    callForResult {
        val url = URL(source)
        val connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 5000
        connection.connect()

        val input: InputStream = connection.inputStream
        val bitmap: BufferedImage? = ImageIO.read(input)
        bitmap
    }.getOrNull()
}