package tech.vivienne.v_page2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "V-Page2"
    ) {
        App()
    }
}