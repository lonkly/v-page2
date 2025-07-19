package tech.vivienne.v_page2.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Window size classes based on Material Design 3 guidelines
 * https://m3.material.io/foundations/layout/applying-layout/window-size-classes
 */
enum class WindowSizeClass {
    Compact,
    Medium,
    Expanded
}

data class WindowSizeClasses(
    val widthSizeClass: WindowSizeClass,
    val heightSizeClass: WindowSizeClass
) {
    val isCompact: Boolean get() = widthSizeClass == WindowSizeClass.Compact
    val isMedium: Boolean get() = widthSizeClass == WindowSizeClass.Medium
    val isExpanded: Boolean get() = widthSizeClass == WindowSizeClass.Expanded
}

@Composable
fun calculateWindowSizeClass(width: Dp, height: Dp): WindowSizeClasses {
    val widthClass = when {
        width < 600.dp -> WindowSizeClass.Compact
        width < 840.dp -> WindowSizeClass.Medium
        else -> WindowSizeClass.Expanded
    }
    
    val heightClass = when {
        height < 480.dp -> WindowSizeClass.Compact
        height < 900.dp -> WindowSizeClass.Medium
        else -> WindowSizeClass.Expanded
    }
    
    return WindowSizeClasses(widthClass, heightClass)
}