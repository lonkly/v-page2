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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.CyberpunkColorScheme

@Composable
fun CyberpunkRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String = "",
    labelColor: Color = CyberpunkTheme.colors.blackPrimary
) {
    val colors = CyberpunkTheme.colors
    val animatedSelection by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = 300, easing = EaseInOutCubic)
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (selected) 1.2f else 1f,
        animationSpec = tween(durationMillis = 200, easing = EaseInOutCubic)
    )

    Row(
        modifier = modifier
            .clickable(enabled = enabled) { onClick() }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(colors.blackPrimary, RoundedCornerShape(15))
                .drawBehind {
                    drawCyberpunkRadioButton(
                        colors = colors,
                        animatedSelection = animatedSelection,
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

private fun DrawScope.drawCyberpunkRadioButton(
    colors: CyberpunkColorScheme,
    animatedSelection: Float,
    animatedScale: Float,
    enabled: Boolean
) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val baseWidth = 8.dp.toPx()
    val baseHeight = 6.dp.toPx()
    val alpha = if (enabled) 1f else 0.5f

    // Background indicator (always visible)
    drawRect(
        color = colors.yellowPrimary.copy(alpha = alpha),
        topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
        size = Size(baseWidth, baseHeight)
    )

    // Animated selection indicator
    if (animatedSelection > 0f) {
        val selectionWidth = baseWidth * animatedScale
        val selectionHeight = baseHeight * animatedScale
        val offsetX = 2.dp.toPx() + (baseWidth * animatedSelection)
        val offsetY = 2.dp.toPx() + (baseHeight * (1 - animatedScale) / 2)

        // Main selection rectangle
        drawRect(
            color = colors.borderGreen.copy(alpha = alpha),
            topLeft = Offset(offsetX, offsetY),
            size = Size(selectionWidth, selectionHeight)
        )

        // Glitch effect
        if (animatedSelection > 0.7f) {
            val glitchOffset = 1.dp.toPx()
            val glitchAlpha = (animatedSelection - 0.7f) * 3.33f // Scale from 0.7-1.0 to 0-1

            // Top glitch line
            drawRect(
                color = colors.neonGreen.copy(alpha = glitchAlpha * alpha * 0.6f),
                topLeft = Offset(offsetX - glitchOffset, offsetY - glitchOffset),
                size = Size(selectionWidth + glitchOffset * 2, 1.dp.toPx())
            )

            // Bottom glitch line
            drawRect(
                color = colors.redPrimary.copy(alpha = glitchAlpha * alpha * 0.6f),
                topLeft = Offset(offsetX - glitchOffset, offsetY + selectionHeight + glitchOffset),
                size = Size(selectionWidth + glitchOffset * 2, 1.dp.toPx())
            )
        }
    }
}

@Composable
fun CyberpunkRadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    labelColor: Color = CyberpunkTheme.colors.blackPrimary
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            CyberpunkRadioButton(
                selected = selectedOption == option,
                onClick = { onOptionSelected(option) },
                label = option,
                enabled = enabled,
                labelColor = labelColor
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkRadioButtonPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Connection Type",
                style = CyberpunkTheme.typography.titleMedium
            )

            var selectedOption by remember { mutableStateOf("Direct") }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CyberpunkRadioButton(
                    selected = selectedOption == "Direct",
                    onClick = { selectedOption = "Direct" },
                    label = "Direct neural link"
                )

                CyberpunkRadioButton(
                    selected = selectedOption == "Wireless",
                    onClick = { selectedOption = "Wireless" },
                    label = "Wireless connection"
                )

                CyberpunkRadioButton(
                    selected = selectedOption == "Secure",
                    onClick = { selectedOption = "Secure" },
                    label = "Secure tunnel"
                )

                CyberpunkRadioButton(
                    selected = selectedOption == "Disabled",
                    onClick = { selectedOption = "Disabled" },
                    label = "Disabled option",
                    enabled = false
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Using RadioButtonGroup",
                style = CyberpunkTheme.typography.titleMedium
            )

            var groupSelection by remember { mutableStateOf("Alpha") }
            CyberpunkRadioButtonGroup(
                options = listOf("Alpha", "Beta", "Gamma", "Delta"),
                selectedOption = groupSelection,
                onOptionSelected = { groupSelection = it }
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkRadioButtonDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Security Level",
                style = CyberpunkTheme.typography.titleMedium
            )

            var selectedSecurity by remember { mutableStateOf("High") }
            CyberpunkRadioButtonGroup(
                options = listOf("Low", "Medium", "High", "Maximum"),
                selectedOption = selectedSecurity,
                onOptionSelected = { selectedSecurity = it },
                labelColor = CyberpunkTheme.colors.neonGreen
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Attack Vector",
                style = CyberpunkTheme.typography.titleMedium
            )

            var selectedAttack by remember { mutableStateOf("Stealth") }
            CyberpunkRadioButtonGroup(
                options = listOf("Stealth", "Brute Force", "Social Engineering"),
                selectedOption = selectedAttack,
                onOptionSelected = { selectedAttack = it },
                labelColor = CyberpunkTheme.colors.redPrimary
            )
        }
    }
}