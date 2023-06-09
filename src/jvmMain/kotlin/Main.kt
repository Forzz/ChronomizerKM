import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        title = "Chronomize",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
