package tech.vivienne.v_page2.design.atoms

import tech.vivienne.v_page2.design.CyberpunkTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun CyberpunkProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    showPercentage: Boolean = true,
    glitchEffect: Boolean = false,
    color: Color = CyberpunkTheme.colors.redPrimary,
    trackColor: Color = CyberpunkTheme.colors.blackPrimary,
    animationDuration: Int = 1000
) {
    val colors = CyberpunkTheme.colors
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = animationDuration, easing = EaseOutCubic)
    )

    val infiniteTransition = rememberInfiniteTransition()
    val glitchOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (glitchEffect) 3f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val glitchAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = if (glitchEffect) 1f else 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(trackColor)
                .drawBehind {
                    // Background dot pattern
                    val dotSize = 2.dp.toPx()
                    val spacing = 5.dp.toPx()

                    for (x in 0 until (size.width / spacing).toInt()) {
                        for (y in 0 until (size.height / spacing).toInt()) {
                            drawCircle(
                                color = colors.yellowPrimary.copy(alpha = 0.3f),
                                radius = dotSize / 2,
                                center = Offset(x * spacing, y * spacing)
                            )
                        }
                    }

                    // Progress fill
                    val progressWidth = size.width * animatedProgress
                    if (progressWidth > 0) {
                        drawRect(
                            color = color,
                            topLeft = Offset(0f, 0f),
                            size = Size(progressWidth + glitchOffset, size.height)
                        )
                    }

                    // Glitch overlay effects
                    if (glitchEffect && animatedProgress > 0.1f) {
                        val segments = 3
                        val segmentWidth = progressWidth / segments

                        for (i in 0 until segments) {
                            val randomOffset = Random.nextFloat() * 2f - 1f
                            val glitchColor = when (i % 3) {
                                0 -> colors.borderGreen
                                1 -> colors.neonGreen
                                else -> colors.orangePrimary
                            }

                            drawRect(
                                color = glitchColor.copy(alpha = glitchAlpha * 0.3f),
                                topLeft = Offset(i * segmentWidth + randomOffset, 0f),
                                size = Size(segmentWidth, size.height)
                            )
                        }
                    }

                    // Progress border
                    if (progressWidth > 0) {
                        drawRect(
                            color = colors.borderGreen,
                            topLeft = Offset(progressWidth - 1.dp.toPx(), 0f),
                            size = Size(2.dp.toPx(), size.height)
                        )
                    }
                }
        )

        if (showPercentage) {
            Text(
                text = "${(animatedProgress * 100).toInt()}%",
                style = CyberpunkTheme.typography.bodyMedium,
                color = colors.blackPrimary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkProgressBarPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Normal Progress",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkProgressBar(
                progress = 0.75f,
                showPercentage = true
            )

            Text(
                text = "Glitch Effect",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkProgressBar(
                progress = 0.45f,
                glitchEffect = true,
                color = CyberpunkTheme.colors.greenPrimary
            )

            Text(
                text = "Blue Progress",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkProgressBar(
                progress = 0.90f,
                color = CyberpunkTheme.colors.bluePrimary,
                showPercentage = false
            )

            Text(
                text = "Low Progress",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkProgressBar(
                progress = 0.15f,
                color = CyberpunkTheme.colors.orangePrimary,
                glitchEffect = true
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkProgressBarDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Neural Link Progress",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkProgressBar(
                progress = 0.68f,
                glitchEffect = true,
                color = CyberpunkTheme.colors.neonGreen
            )

            Text(
                text = "System Breach",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkProgressBar(
                progress = 0.32f,
                color = CyberpunkTheme.colors.redPrimary,
                glitchEffect = true
            )
        }
    }
}