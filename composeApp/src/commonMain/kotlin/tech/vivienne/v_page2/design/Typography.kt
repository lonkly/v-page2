package tech.vivienne.v_page2.design

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import tech.vivienne.composeapp.generated.resources.Hack_Bold
import tech.vivienne.composeapp.generated.resources.Hack_BoldItalic
import tech.vivienne.composeapp.generated.resources.Hack_Italic
import tech.vivienne.composeapp.generated.resources.Hack_Regular
import tech.vivienne.composeapp.generated.resources.Res

@Composable
fun hackFontFamily(): FontFamily {
    return FontFamily(
        Font(Res.font.Hack_Regular, FontWeight.Normal),
        Font(Res.font.Hack_Italic, FontWeight.Normal),
        Font(Res.font.Hack_Bold, FontWeight.Bold),
        Font(Res.font.Hack_BoldItalic, FontWeight.Bold)
    )
}

@Composable
fun cyberpunkTypography(): Typography {
    val hackFamily = hackFontFamily()
    
    return Typography(
        headlineLarge = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.W200,
            fontSize = 32.sp,
            lineHeight = 35.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.W300,
            fontSize = 27.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.W500,
            fontSize = 22.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            lineHeight = 19.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 2.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 19.sp,
            letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        labelLarge = TextStyle(
            fontFamily = hackFamily,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 2.sp
        )
    )
}