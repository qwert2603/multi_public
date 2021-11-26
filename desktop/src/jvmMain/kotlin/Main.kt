import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.qwert2603.multi_public.common.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}