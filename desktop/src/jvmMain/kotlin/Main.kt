import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.qwert2603.multi_public.common.App
import com.qwert2603.multi_public.common.di.Di
import com.qwert2603.multi_public.common.di.PlatformDi

fun main() = application {
    Di.startDi(PlatformDi())
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}