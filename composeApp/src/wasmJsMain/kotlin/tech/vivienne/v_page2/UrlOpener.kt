package tech.vivienne.v_page2

import kotlinx.browser.window

actual fun openUrl(url: String) {
    window.open(url, "_blank")
}