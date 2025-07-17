package tech.vivienne.v_page2.design.atoms

import tech.vivienne.v_page2.design.CyberpunkTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CyberpunkLoadingSpinner(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = CyberpunkTheme.colors.borderGreen,
    secondaryColor: Color = CyberpunkTheme.colors.redPrimary,
    strokeWidth: Dp = 3.dp,
    animationDuration: Int = 1000
) {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val glitchEffect by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val pulseEffect by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = modifier
            .size(size)
            .rotate(rotation)
    ) {
        val center = Offset(this.size.width / 2f, this.size.height / 2f)
        val radius = this.size.width / 3f
        val strokeWidthPx = strokeWidth.toPx()

        // Draw spinning arcs
        for (i in 0 until 8) {
            val angle = (i * 45f)
            val startAngle = angle - 20f
            val sweepAngle = 40f
            val alpha = 0.3f + (glitchEffect * 0.7f)

            // Outer arc
            drawArc(
                color = color.copy(alpha = alpha),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
                topLeft = center - Offset(radius, radius),
                size = Size(radius * 2, radius * 2)
            )

            // Secondary color accent
            if (i % 2 == 0) {
                drawArc(
                    color = secondaryColor.copy(alpha = alpha * 0.7f),
                    startAngle = startAngle + 10f,
                    sweepAngle = sweepAngle - 20f,
                    useCenter = false,
                    style = Stroke(width = strokeWidthPx * 0.5f, cap = StrokeCap.Round),
                    topLeft = center - Offset(radius * 0.8f, radius * 0.8f),
                    size = Size(radius * 1.6f, radius * 1.6f)
                )
            }
        }

        // Inner pulsing circle
        drawCircle(
            color = color.copy(alpha = pulseEffect - 0.8f),
            radius = 4.dp.toPx() * pulseEffect,
            center = center,
            style = Stroke(width = strokeWidthPx * 0.5f)
        )

        // Center dot
        drawCircle(
            color = color,
            radius = 2.dp.toPx(),
            center = center
        )
    }
}

@Composable
fun CyberpunkLoadingSpinnerWithText(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    color: Color = CyberpunkTheme.colors.borderGreen,
    textColor: Color = CyberpunkTheme.colors.blackPrimary
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CyberpunkLoadingSpinner(
            size = size,
            color = color
        )

        Text(
            text = text,
            style = CyberpunkTheme.typography.bodyMedium,
            color = textColor
        )
    }
}

@Preview
@Composable
fun CyberpunkLoadingSpinnerPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Default Spinner",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkLoadingSpinner(size = 60.dp)

            Text(
                text = "Red Spinner",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkLoadingSpinner(
                size = 50.dp,
                color = CyberpunkTheme.colors.redPrimary,
                secondaryColor = CyberpunkTheme.colors.orangePrimary
            )

            Text(
                text = "Blue Spinner",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkLoadingSpinner(
                size = 40.dp,
                color = CyberpunkTheme.colors.bluePrimary,
                strokeWidth = 2.dp
            )

            Text(
                text = "With Text",
                style = CyberpunkTheme.typography.bodyMedium
            )
            CyberpunkLoadingSpinnerWithText(
                text = "Connecting...",
                size = 45.dp,
                color = CyberpunkTheme.colors.neonGreen
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkLoadingSpinnerDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            CyberpunkLoadingSpinnerWithText(
                text = "Neural Link Establishing...",
                size = 60.dp,
                color = CyberpunkTheme.colors.neonGreen
            )

            CyberpunkLoadingSpinnerWithText(
                text = "Breaching System...",
                size = 50.dp,
                color = CyberpunkTheme.colors.redPrimary,
                textColor = CyberpunkTheme.colors.redPrimary
            )
        }
    }
}