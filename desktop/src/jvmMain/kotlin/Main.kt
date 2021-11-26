import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.qwert2603.multi_public.common.App
import com.qwert2603.multi_public.common.di.Di

fun main() = application {
    Di.startDi()
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}