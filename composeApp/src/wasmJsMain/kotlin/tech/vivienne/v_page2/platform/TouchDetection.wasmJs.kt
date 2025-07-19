package tech.vivienne.v_page2.platform

import androidx.compose.runtime.Composable

// Top-level property to check for touch support
private val isTouchSupported: Boolean = js("'ontouchstart' in window || (navigator.maxTouchPoints && navigator.maxTouchPoints > 0)")

@Composable
actual fun isTouchDevice(): Boolean {
    return isTouchSupported
}