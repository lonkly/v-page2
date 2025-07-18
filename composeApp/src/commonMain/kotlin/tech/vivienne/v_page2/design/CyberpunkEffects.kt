package tech.vivienne.v_page2.design

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.sin

// Animation Extensions
@Composable
fun Modifier.animateBlinking(): Modifier {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    return this.graphicsLayer(alpha = alpha)
}

@Composable
fun Modifier.scanningEffect(
    isActive: Boolean = true,
    direction: ScanDirection = ScanDirection.Horizontal,
    color: Color = CyberpunkTheme.colors.borderGreen
): Modifier {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    return if (isActive) {
        this.drawWithContent {
            drawContent()

            if (direction == ScanDirection.Horizontal) {
                val y = size.height * animatedValue
                drawLine(
                    color = color,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = 3.dp.toPx(),
                    pathEffect = PathEffect.cornerPathEffect(radius = 2.dp.toPx())
                )
            } else {
                val x = size.width * animatedValue
                drawLine(
                    color = color,
                    start = Offset(x, 0f),
                    end = Offset(x, size.height),
                    strokeWidth = 3.dp.toPx(),
                    pathEffect = PathEffect.cornerPathEffect(radius = 2.dp.toPx())
                )
            }
        }
    } else {
        this
    }
}

@Composable
fun Modifier.glitchEffect(
    isActive: Boolean = true,
    intensity: Float = 1f
): Modifier {
    val infiniteTransition = rememberInfiniteTransition()

    // Create a more subtle, digital glitch effect
    val glitchOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0f at 0
                0f at 2700
                2f * intensity at 2750
                -1f * intensity at 2800
                0f at 2850
                -2f * intensity at 2900
                0f at 3000
            },
            repeatMode = RepeatMode.Restart
        )
    )

    val chromaOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 4000
                0f at 0
                0f at 3700
                1f * intensity at 3750
                -0.5f * intensity at 3800
                0f at 3850
            },
            repeatMode = RepeatMode.Restart
        )
    )

    return if (isActive) {
        this.drawWithContent {
            // Draw main content
            drawContent()
            
            // Add subtle chromatic aberration effect
            if (chromaOffset != 0f) {
                drawContent()
                drawRect(
                    color = Color.Red.copy(alpha = 0.1f),
                    topLeft = Offset(chromaOffset.dp.toPx(), 0f),
                    size = size
                )
                drawRect(
                    color = Color.Cyan.copy(alpha = 0.1f),
                    topLeft = Offset(-chromaOffset.dp.toPx(), 0f),
                    size = size
                )
            }
        }.graphicsLayer(
            translationX = glitchOffset
        )
    } else {
        this
    }
}

@Composable
fun Modifier.cyberShake(
    isActive: Boolean = true,
    intensity: Float = 1f
): Modifier {
    val infiniteTransition = rememberInfiniteTransition()

    val shakeX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isActive) 5f * intensity else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(50, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val shakeY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isActive) 3f * intensity else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(75, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    return if (isActive) {
        this.graphicsLayer(
            translationX = shakeX,
            translationY = shakeY
        )
    } else {
        this
    }
}

enum class ScanDirection {
    Horizontal, Vertical
}