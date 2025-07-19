package tech.vivienne.v_page2.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CyberpunkColorScheme(
    val yellowPrimary: Color,
    val redPrimary: Color,
    val bluePrimary: Color,
    val greenPrimary: Color,
    val purplePrimary: Color,
    val neonGreen: Color,
    val blackPrimary: Color,
    val orangePrimary: Color,
    val borderGreen: Color,
    val backgroundScanned: Color,
    val glitchColor: Color,
    val white: Color
)

val LocalCyberpunkColors = staticCompositionLocalOf {
    CyberpunkColorScheme(
        yellowPrimary = CyberpunkColors.YellowPrimary,
        redPrimary = CyberpunkColors.RedPrimary,
        bluePrimary = CyberpunkColors.BluePrimary,
        greenPrimary = CyberpunkColors.GreenPrimary,
        purplePrimary = CyberpunkColors.PurplePrimary,
        neonGreen = CyberpunkColors.NeonGreen,
        blackPrimary = CyberpunkColors.BlackPrimary,
        orangePrimary = CyberpunkColors.OrangePrimary,
        borderGreen = CyberpunkColors.BorderGreen,
        backgroundScanned = CyberpunkColors.YellowOpacity,
        glitchColor = CyberpunkColors.BorderGreen,
        white = CyberpunkColors.White
    )
}

private val CyberpunkLightColorScheme = lightColorScheme(
    primary = CyberpunkColors.RedPrimary,
    onPrimary = CyberpunkColors.White,
    secondary = CyberpunkColors.BluePrimary,
    onSecondary = CyberpunkColors.White,
    tertiary = CyberpunkColors.GreenPrimary,
    onTertiary = CyberpunkColors.White,
    background = CyberpunkColors.YellowPrimary,
    onBackground = CyberpunkColors.BlackPrimary,
    surface = CyberpunkColors.YellowPrimary,
    onSurface = CyberpunkColors.BlackPrimary,
    surfaceVariant = CyberpunkColors.BlackPrimary,
    onSurfaceVariant = CyberpunkColors.YellowPrimary,
    error = CyberpunkColors.RedPrimary,
    onError = CyberpunkColors.White,
    outline = CyberpunkColors.BorderGreen,
    outlineVariant = CyberpunkColors.BorderGreen,
    inverseSurface = CyberpunkColors.BlackPrimary,
    inverseOnSurface = CyberpunkColors.YellowPrimary,
    inversePrimary = CyberpunkColors.YellowPrimary
)

private val CyberpunkDarkColorScheme = darkColorScheme(
    primary = CyberpunkColors.RedPrimary,
    onPrimary = CyberpunkColors.White,
    secondary = CyberpunkColors.BluePrimary,
    onSecondary = CyberpunkColors.White,
    tertiary = CyberpunkColors.GreenPrimary,
    onTertiary = CyberpunkColors.White,
    background = CyberpunkColors.BlackPrimary,
    onBackground = CyberpunkColors.YellowPrimary,
    surface = CyberpunkColors.BlackPrimary,
    onSurface = CyberpunkColors.YellowPrimary,
    surfaceVariant = CyberpunkColors.YellowPrimary,
    onSurfaceVariant = CyberpunkColors.BlackPrimary,
    error = CyberpunkColors.RedPrimary,
    onError = CyberpunkColors.White,
    outline = CyberpunkColors.BorderGreen,
    outlineVariant = CyberpunkColors.BorderGreen,
    inverseSurface = CyberpunkColors.YellowPrimary,
    inverseOnSurface = CyberpunkColors.BlackPrimary,
    inversePrimary = CyberpunkColors.BlackPrimary
)

@Composable
fun CyberpunkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        CyberpunkDarkColorScheme
    } else {
        CyberpunkLightColorScheme
    }

    val cyberpunkColors = CyberpunkColorScheme(
        yellowPrimary = CyberpunkColors.YellowPrimary,
        redPrimary = CyberpunkColors.RedPrimary,
        bluePrimary = CyberpunkColors.BluePrimary,
        greenPrimary = CyberpunkColors.GreenPrimary,
        purplePrimary = CyberpunkColors.PurplePrimary,
        neonGreen = CyberpunkColors.NeonGreen,
        blackPrimary = CyberpunkColors.BlackPrimary,
        orangePrimary = CyberpunkColors.OrangePrimary,
        borderGreen = CyberpunkColors.BorderGreen,
        backgroundScanned = CyberpunkColors.YellowOpacity,
        glitchColor = CyberpunkColors.BorderGreen,
        white = CyberpunkColors.White
    )

    CompositionLocalProvider(
        LocalCyberpunkColors provides cyberpunkColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = cyberpunkTypography(),
            content = content
        )
    }
}

// Extension for accessing custom colors
object CyberpunkTheme {
    val colors: CyberpunkColorScheme
        @Composable
        get() = LocalCyberpunkColors.current

    val typography: Typography
        @Composable
        get() = cyberpunkTypography()

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}