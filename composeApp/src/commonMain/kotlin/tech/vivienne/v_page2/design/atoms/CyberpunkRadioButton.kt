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
                .size(width = 20.dp, height = 14.dp)
                .background(colors.blackPrimary, RoundedCornerShape(15))
                .drawBehind {
                    drawCyberpunkRadioButton(
                        colors = colors,
                        selected = selected,
                        animatedSelection = animatedSelection,
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
    selected: Boolean,
    animatedSelection: Float,
    enabled: Boolean
) {
    // CSS-based dimensions
    val indicatorWidth = 8.dp.toPx()
    val indicatorHeight = 6.dp.toPx()
    val alpha = if (enabled) 1f else 0.5f
    
    // Position based on CSS - starts at left: 2px, moves to left: 10px when checked
    val leftPosition = 2.dp.toPx() + (8.dp.toPx() * animatedSelection)
    val topPosition = 2.dp.toPx()
    
    // Color based on state
    val indicatorColor = if (selected) colors.borderGreen else colors.yellowPrimary

    // Draw the indicator rectangle (after pseudo-element)
    drawRect(
        color = indicatorColor.copy(alpha = alpha),
        topLeft = Offset(leftPosition, topPosition),
        size = Size(indicatorWidth, indicatorHeight)
    )
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