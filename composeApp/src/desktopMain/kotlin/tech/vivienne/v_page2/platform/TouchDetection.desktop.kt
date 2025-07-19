package tech.vivienne.v_page2.platform

import androidx.compose.runtime.Composable

@Composable
actual fun isTouchDevice(): Boolean {
    // Desktop doesn't have touch support
    return false
}