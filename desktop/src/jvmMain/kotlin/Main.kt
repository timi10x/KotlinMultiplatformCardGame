import me.aliyuolalekan.common.App
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "WhotPlus") {
        MaterialTheme {
            App()
        }
    }
}