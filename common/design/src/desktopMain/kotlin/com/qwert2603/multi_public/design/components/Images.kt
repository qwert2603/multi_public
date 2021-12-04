package com.qwert2603.multi_public.design.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import com.qwert2603.multi_public.util.callForResult
import com.qwert2603.multi_public.util.getOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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
    placeHolder: ImageVector?,
    modifier: Modifier,
) {
    val imageAsset: ImageBitmap? = imageUrl?.let { fetchImage(it) }

    when {
        imageAsset != null -> {
            Image(
                imageAsset,
                contentDescription = null,
                modifier = modifier,
            )
        }
        placeHolder != null -> {
            Image(
                placeHolder,
                contentDescription = null,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun fetchImage(url: String): ImageBitmap? {
    val imageBitmap by produceState<ImageBitmap?>(
        key1 = url,
        initialValue = null,
        producer = {
            imagesCacheQueue[url]?.let { cached ->
                value = cached
                return@produceState
            }

            val imageBitmap = loadFullImage(url)
                ?.let(::toByteArray)
                ?.let(::makeFromEncoded)
                ?.toComposeImageBitmap()

            if (imageBitmap != null) {
                imagesCacheMutex.withLock {
                    imagesCacheQueue[url] = imageBitmap
                    if (imagesCacheQueue.size > imagesCacheSize) {
                        imagesCacheQueue.remove(imagesCacheQueue.keys.first())
                    }
                }
            }

            value = imageBitmap
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

private val imagesCacheMutex = Mutex()
private const val imagesCacheSize = 100
private val imagesCacheQueue = LinkedHashMap<String, ImageBitmap>()
