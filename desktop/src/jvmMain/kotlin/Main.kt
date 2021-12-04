import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.qwert2603.multi_public.common.App
import com.qwert2603.multi_public.common.di.Di
import com.qwert2603.multi_public.common.di.PlatformDi
import com.qwert2603.multi_public.common.presentation.root.RootComponent

fun main() = application {
    Di.startDi(PlatformDi())
    Window(onCloseRequest = ::exitApplication) {
        val lifecycleRegistry = LifecycleRegistry()
        lifecycleRegistry.resume() // todo: consume window state

        val componentContext = DefaultComponentContext(lifecycleRegistry)
        val rootComponent = RootComponent(componentContext)

        App(rootComponent)
    }
}