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

@Composable
fun CyberpunkTitle(
    text: String,
    modifier: Modifier = Modifier,
    level: CyberpunkTitleLevel = CyberpunkTitleLevel.H1,
    glitchEffect: Boolean = false,
    showCursor: Boolean = true,
    color: Color = CyberpunkTheme.colors.blackPrimary
) {
    val colors = CyberpunkTheme.colors
    val style = when (level) {
        CyberpunkTitleLevel.H1 -> CyberpunkTheme.typography.headlineLarge
        CyberpunkTitleLevel.H2 -> CyberpunkTheme.typography.headlineMedium
        CyberpunkTitleLevel.H3 -> CyberpunkTheme.typography.headlineSmall
        CyberpunkTitleLevel.H4 -> CyberpunkTheme.typography.titleLarge
    }

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
                    .drawBehind {
                        // Draw the title underline
                        val strokeWidth = underlineHeight.toPx()
                        val y = size.height + 15.dp.toPx()

                        // Create underline path based on level
                        val underlinePath = when (level) {
                            CyberpunkTitleLevel.H1 -> Path().apply {
                                moveTo(0f, 0f)
                                lineTo(85f, 0f)
                                lineTo(90f, 5f)
                                lineTo(100f, 5f)
                                lineTo(100f, 6f)
                                lineTo(85f, 6f)
                                lineTo(80f, 10f)
                                lineTo(0f, 10f)
                                close()
                            }
                            CyberpunkTitleLevel.H2 -> Path().apply {
                                moveTo(0f, 5f)
                                lineTo(35f, 5f)
                                lineTo(40f, 0f)
                                lineTo(85f, 0f)
                                lineTo(90f, 5f)
                                lineTo(100f, 5f)
                                lineTo(100f, 6f)
                                lineTo(85f, 6f)
                                lineTo(80f, 10f)
                                lineTo(20f, 10f)
                                lineTo(15f, 6f)
                                lineTo(0f, 6f)
                                close()
                            }
                            else -> Path().apply {
                                moveTo(0f, 0f)
                                lineTo(size.width, 0f)
                                lineTo(size.width, strokeWidth)
                                lineTo(0f, strokeWidth)
                                close()
                            }
                        }

                        drawPath(
                            path = underlinePath,
                            color = colors.blackPrimary
                        )
                    }
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

        Spacer(modifier = Modifier.height(15.dp))
    }
}

enum class CyberpunkTitleLevel {
    H1, H2, H3, H4
}

