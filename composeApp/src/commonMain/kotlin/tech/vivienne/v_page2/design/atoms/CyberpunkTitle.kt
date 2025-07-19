package tech.vivienne.v_page2.design.atoms


import tech.vivienne.v_page2.design.CyberpunkTheme
import tech.vivienne.v_page2.design.animateBlinking
import tech.vivienne.v_page2.design.glitchEffect
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle

@Composable
fun CyberpunkTitle(
    text: String,
    modifier: Modifier = Modifier,
    level: CyberpunkTitleLevel = CyberpunkTitleLevel.H1,
    glitchEffect: Boolean = false,
    showCursor: Boolean = true,
    color: Color = CyberpunkTheme.colors.blackPrimary,
    showUnderline: Boolean = true,
    useGlitchingUnderline: Boolean = false,
    textStyle: TextStyle? = null
) {
    val colors = CyberpunkTheme.colors
    val defaultStyle = when (level) {
        CyberpunkTitleLevel.H1 -> CyberpunkTheme.typography.headlineLarge
        CyberpunkTitleLevel.H2 -> CyberpunkTheme.typography.headlineMedium
        CyberpunkTitleLevel.H3 -> CyberpunkTheme.typography.headlineSmall
        CyberpunkTitleLevel.H4 -> CyberpunkTheme.typography.titleLarge
    }
    val style = textStyle ?: defaultStyle

    val underlineHeight = when (level) {
        CyberpunkTitleLevel.H1 -> 10.dp
        CyberpunkTitleLevel.H2 -> 8.dp
        CyberpunkTitleLevel.H3 -> 6.dp
        CyberpunkTitleLevel.H4 -> 4.dp
    }

    Column(modifier = modifier) {
        Row {
            Text(
                text = text,
                style = style,
                color = color,
                fontWeight = when (level) {
                    CyberpunkTitleLevel.H1 -> FontWeight.W200
                    CyberpunkTitleLevel.H2 -> FontWeight.W300
                    CyberpunkTitleLevel.H3 -> FontWeight.W500
                    CyberpunkTitleLevel.H4 -> FontWeight.W700
                },
                modifier = Modifier
                    .glitchEffect(isActive = glitchEffect, intensity = 0.7f)
                    .then(
                        if (showUnderline && !useGlitchingUnderline) {
                            Modifier.drawBehind {
                                // Draw the title underline (before pseudo-element)
                                val y = size.height - 2.dp.toPx()

                                // Create underline path based on level matching CSS clip-paths
                                val underlinePath = when (level) {
                                    CyberpunkTitleLevel.H1 -> Path().apply {
                                        // CSS: polygon(0px 0px, 85px 0px, 90px 5px, 100% 5px, 100% 6px, 85px 6px, 80px 10px, 0px 10px)
                                        moveTo(0f, y)
                                        lineTo(85.dp.toPx(), y)
                                        lineTo(90.dp.toPx(), y + 5.dp.toPx())
                                        lineTo(size.width, y + 5.dp.toPx())
                                        lineTo(size.width, y + 6.dp.toPx())
                                        lineTo(85.dp.toPx(), y + 6.dp.toPx())
                                        lineTo(80.dp.toPx(), y + 10.dp.toPx())
                                        lineTo(0f, y + 10.dp.toPx())
                                        close()
                                    }
                                    CyberpunkTitleLevel.H2 -> Path().apply {
                                        // CSS: polygon(0px 5px, 35px 5px, 40px 0px, 85px 0px, 90px 5px, 100% 5px, 100% 6px, 85px 6px, 80px 10px, 20px 10px, 15px 6px, 0px 6px)
                                        moveTo(0f, y + 5.dp.toPx())
                                        lineTo(35.dp.toPx(), y + 5.dp.toPx())
                                        lineTo(40.dp.toPx(), y)
                                        lineTo(85.dp.toPx(), y)
                                        lineTo(90.dp.toPx(), y + 5.dp.toPx())
                                        lineTo(size.width, y + 5.dp.toPx())
                                        lineTo(size.width, y + 6.dp.toPx())
                                        lineTo(85.dp.toPx(), y + 6.dp.toPx())
                                        lineTo(80.dp.toPx(), y + 10.dp.toPx())
                                        lineTo(20.dp.toPx(), y + 10.dp.toPx())
                                        lineTo(15.dp.toPx(), y + 6.dp.toPx())
                                        lineTo(0f, y + 6.dp.toPx())
                                        close()
                                    }
                                    CyberpunkTitleLevel.H3 -> Path().apply {
                                        // CSS: polygon(0px 5px, 10px 5px, 15px 0px, 40px 0px, 45px 5px, 100% 5px, 100% 6px, 31px 6px, 27px 2px, 15px 2px, 8px 10px, 0px 10px)
                                        moveTo(0f, y + 5.dp.toPx())
                                        lineTo(10.dp.toPx(), y + 5.dp.toPx())
                                        lineTo(15.dp.toPx(), y)
                                        lineTo(40.dp.toPx(), y)
                                        lineTo(45.dp.toPx(), y + 5.dp.toPx())
                                        lineTo(size.width, y + 5.dp.toPx())
                                        lineTo(size.width, y + 6.dp.toPx())
                                        lineTo(31.dp.toPx(), y + 6.dp.toPx())
                                        lineTo(27.dp.toPx(), y + 2.dp.toPx())
                                        lineTo(15.dp.toPx(), y + 2.dp.toPx())
                                        lineTo(8.dp.toPx(), y + 10.dp.toPx())
                                        lineTo(0f, y + 10.dp.toPx())
                                        close()
                                    }
                                    CyberpunkTitleLevel.H4 -> Path().apply {
                                        // CSS: polygon(0px 3px, 15px 3px, 20px 0px, 80px 0px, 85px 3px, 100% 3px, 100% 4px, 85px 4px, 80px 7px, 20px 7px, 15px 4px, 0px 4px)
                                        moveTo(0f, y + 3.dp.toPx())
                                        lineTo(15.dp.toPx(), y + 3.dp.toPx())
                                        lineTo(20.dp.toPx(), y)
                                        lineTo(80.dp.toPx(), y)
                                        lineTo(85.dp.toPx(), y + 3.dp.toPx())
                                        lineTo(size.width, y + 3.dp.toPx())
                                        lineTo(size.width, y + 4.dp.toPx())
                                        lineTo(85.dp.toPx(), y + 4.dp.toPx())
                                        lineTo(80.dp.toPx(), y + 7.dp.toPx())
                                        lineTo(20.dp.toPx(), y + 7.dp.toPx())
                                        lineTo(15.dp.toPx(), y + 4.dp.toPx())
                                        lineTo(0f, y + 4.dp.toPx())
                                        close()
                                    }
                                }

                                drawPath(
                                    path = underlinePath,
                                    color = color
                                )
                            }
                        } else {
                            Modifier
                        }
                    )
            )

            if (showCursor) {
                Text(
                    text = "_",
                    style = style,
                    color = color,
                    modifier = Modifier
                        .animateBlinking()
                        .offset(x = 4.dp)
                )
            }
        }
        
        // Add glitching separator if enabled
        if (showUnderline && useGlitchingUnderline) {
            CyberPunkSeparator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = when (level) {
                        CyberpunkTitleLevel.H1 -> 2.dp
                        CyberpunkTitleLevel.H2 -> 1.dp
                        CyberpunkTitleLevel.H3 -> 0.dp
                        CyberpunkTitleLevel.H4 -> 0.dp
                    }),
                color = color,
                isGlitched = true
            )
        }
    }
}

enum class CyberpunkTitleLevel {
    H1, H2, H3, H4
}

