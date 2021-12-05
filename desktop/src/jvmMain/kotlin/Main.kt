import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.qwert2603.multi_public.common.App
import com.qwert2603.multi_public.common.di.Di
import com.qwert2603.multi_public.common.di.PlatformDi
import com.qwert2603.multi_public.common.presentation.root.RootComponent

fun main() = application {
    Di.startDi(PlatformDi())

    val windowState = rememberWindowState()

    val lifecycleRegistry = LifecycleRegistry()
    LifecycleController(lifecycleRegistry, windowState)

    val componentContext = DefaultComponentContext(lifecycleRegistry)
    val rootComponent = RootComponent(componentContext)

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Multi public",
    ) {
        App(rootComponent)
    }
}