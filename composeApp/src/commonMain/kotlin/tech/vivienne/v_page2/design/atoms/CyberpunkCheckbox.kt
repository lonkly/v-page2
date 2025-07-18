package tech.vivienne.v_page2.design.atoms

import tech.vivienne.v_page2.design.CyberpunkTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.CyberpunkColorScheme

@Composable
fun CyberpunkCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String = "",
    labelColor: Color = CyberpunkTheme.colors.blackPrimary
) {
    val colors = CyberpunkTheme.colors
    val animatedCheckProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(durationMillis = 200, easing = EaseInOutCubic)
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (checked) 1.1f else 1f,
        animationSpec = tween(durationMillis = 150, easing = EaseInOutCubic)
    )

    Row(
        modifier = modifier
            .clickable(enabled = enabled) {
                onCheckedChange(!checked)
            }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(colors.blackPrimary, RoundedCornerShape(15))
                .drawBehind {
                    drawCyberpunkCheckbox(
                        colors = colors,
                        checked = checked,
                        animatedCheckProgress = animatedCheckProgress,
                        animatedScale = animatedScale,
                        enabled = enabled
                    )
                }
        )

        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = CyberpunkTheme.typography.bodyMedium,
                color = if (enabled) labelColor else labelColor.copy(alpha = 0.5f),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

private fun DrawScope.drawCyberpunkCheckbox(
    colors: CyberpunkColorScheme,
    checked: Boolean,
    animatedCheckProgress: Float,
    animatedScale: Float,
    enabled: Boolean
) {
    // CSS-based dimensions
    val circleSize = 8.dp.toPx()
    val circleRadius = circleSize / 2
    val strokeWidth = 2.dp.toPx()
    val indicatorWidth = 2.dp.toPx()
    val indicatorHeight = 7.dp.toPx()
    
    // Position for circle (before pseudo-element)
    val circleLeft = 4.dp.toPx()
    val circleTop = 5.dp.toPx()
    val circleCenter = Offset(circleLeft + circleRadius, circleTop + circleRadius)
    
    // Position for vertical line (after pseudo-element)
    val lineLeft = 9.dp.toPx()
    val lineTop = 3.dp.toPx()

    // Draw circle (before pseudo-element) - always visible
    val circleColor = if (checked) colors.borderGreen else colors.yellowPrimary
    val alpha = if (enabled) 1f else 0.5f
    
    // Bottom and side parts of circle (not top)
    drawArc(
        color = circleColor.copy(alpha = alpha),
        startAngle = 45f,
        sweepAngle = 270f,
        useCenter = false,
        topLeft = Offset(circleLeft, circleTop),
        size = Size(circleSize, circleSize),
        style = Stroke(width = strokeWidth)
    )

    // Draw vertical line (after pseudo-element) - always visible
    val lineColor = if (checked) colors.borderGreen else colors.yellowPrimary
    drawRect(
        color = lineColor.copy(alpha = alpha),
        topLeft = Offset(lineLeft, lineTop),
        size = Size(indicatorWidth, indicatorHeight)
    )
}

@Preview
@Composable
fun CyberpunkCheckboxPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var checked1 by remember { mutableStateOf(false) }
            CyberpunkCheckbox(
                checked = checked1,
                onCheckedChange = { checked1 = it },
                label = "Enable neural link"
            )

            var checked2 by remember { mutableStateOf(true) }
            CyberpunkCheckbox(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                label = "Auto-connect to network"
            )

            var checked3 by remember { mutableStateOf(false) }
            CyberpunkCheckbox(
                checked = checked3,
                onCheckedChange = { checked3 = it },
                label = "Accept terms and conditions"
            )

            var checked4 by remember { mutableStateOf(true) }
            CyberpunkCheckbox(
                checked = checked4,
                onCheckedChange = { checked4 = it },
                label = "Disabled option",
                enabled = false
            )

            // Checkbox without label
            var checked5 by remember { mutableStateOf(false) }
            CyberpunkCheckbox(
                checked = checked5,
                onCheckedChange = { checked5 = it }
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkCheckboxDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var checked1 by remember { mutableStateOf(false) }
            CyberpunkCheckbox(
                checked = checked1,
                onCheckedChange = { checked1 = it },
                label = "Breach firewall",
                labelColor = CyberpunkTheme.colors.redPrimary
            )

            var checked2 by remember { mutableStateOf(true) }
            CyberpunkCheckbox(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                label = "Stealth mode active",
                labelColor = CyberpunkTheme.colors.neonGreen
            )

            var checked3 by remember { mutableStateOf(false) }
            CyberpunkCheckbox(
                checked = checked3,
                onCheckedChange = { checked3 = it },
                label = "Override security protocol"
            )
        }
    }
}