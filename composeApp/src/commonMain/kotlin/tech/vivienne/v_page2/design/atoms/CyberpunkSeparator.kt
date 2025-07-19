package tech.vivienne.v_page2.design.atoms

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Composable
fun CyberPunkSeparator(
    modifier: Modifier = Modifier,
    color: Color = CyberpunkTheme.colors.blackPrimary,
    isGlitched: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition()
    
    val skewAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0f at 0 using LinearEasing
                0f at 450 using LinearEasing
                20f at 480 using LinearEasing
                -20f at 600 using LinearEasing
                0f at 630 using LinearEasing
                0f at 1950 using LinearEasing
                20f at 1980 using LinearEasing
                40f at 2100 using LinearEasing
                0f at 2130 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        )
    )
    
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0f at 0 using LinearEasing
                0f at 450 using LinearEasing
                -20f at 480 using LinearEasing
                20f at 600 using LinearEasing
                0f at 630 using LinearEasing
                0f at 1950 using LinearEasing
                -5f at 1980 using LinearEasing
                20f at 2100 using LinearEasing
                0f at 2130 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        )
    )
    
    val scaleX by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                1f at 0 using LinearEasing
                1f at 1950 using LinearEasing
                -1f at 1980 using LinearEasing
                -1f at 2100 using LinearEasing
                0f at 2130 using LinearEasing
                1f at 2131 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        )
    )
    
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(14.dp)
    ) {
        if (isGlitched) {
            drawGlitchedSeparator(color, skewAngle, offsetX, scaleX)
        } else {
            drawSimpleSeparator(color)
        }
    }
}

private fun DrawScope.drawSimpleSeparator(color: Color) {
    val path = Path().apply {
        // CSS: polygon(0px 7px, 100% 7px, 100% 8px, 0px 8px)
        // Creates a 1px line at position 7-8px in a 14px height
        moveTo(0f, 7.dp.toPx())
        lineTo(size.width, 7.dp.toPx())
        lineTo(size.width, 8.dp.toPx())
        lineTo(0f, 8.dp.toPx())
        close()
    }
    
    clipPath(path) {
        drawRect(color)
    }
}

private fun DrawScope.drawGlitchedSeparator(
    color: Color,
    skewAngle: Float,
    offsetX: Float,
    scaleX: Float
) {
    translate(offsetX.dp.toPx(), 0f) {
        val path = if (skewAngle != 0f) {
            Path().apply {
                val glitchPattern = listOf(
                    1f to 0f, 0f to 0f, 0f to 0f, 8f to 14f, 13f to 14f,
                    22f to 7f, 42f to 6f, 49f to 2f, 100f to 2f, 100f to 0f,
                    42f to 0f, 35f to 5f, 22f to 6f, 13f to 13f, 9f to 13f
                )
                
                glitchPattern.forEachIndexed { index, (x, y) ->
                    val xPos = (x / 100f) * size.width
                    val yPos = (y / 14f) * size.height
                    if (index == 0) {
                        moveTo(xPos, yPos)
                    } else {
                        lineTo(xPos, yPos)
                    }
                }
                close()
            }
        } else {
            Path().apply {
                moveTo(0f, size.height / 2f)
                lineTo(size.width, size.height / 2f)
                lineTo(size.width, size.height / 2f + 1.dp.toPx())
                lineTo(0f, size.height / 2f + 1.dp.toPx())
                close()
            }
        }
        
        clipPath(path) {
            drawRect(color)
        }
    }
}