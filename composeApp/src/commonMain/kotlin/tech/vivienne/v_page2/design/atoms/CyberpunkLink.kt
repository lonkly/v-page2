package tech.vivienne.v_page2.design.atoms

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Composable
fun CyberPunkLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = CyberpunkTheme.typography.bodyMedium,
    color: Color = CyberpunkTheme.colors.borderGreen,
    underlineColor: Color = CyberpunkTheme.colors.borderGreen
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    val infiniteTransition = rememberInfiniteTransition()
    val scanPosition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Text(
            text = text,
            style = textStyle.copy(
                fontWeight = textStyle.fontWeight ?: FontWeight.Medium
            ),
            color = color,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        
        // Static underline with glow
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 1.dp)
        ) {
            drawStaticUnderline(underlineColor)
        }
        
        // Animated scanning line on hover
        if (isHovered) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .matchParentSize()
                    .align(Alignment.BottomCenter)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawScanningLine(underlineColor, scanPosition)
                }
            }
        }
    }
}

private fun DrawScope.drawStaticUnderline(color: Color) {
    val underlineY = size.height - 1.5.dp.toPx()
    
    // Main underline
    drawRect(
        color = color,
        topLeft = Offset(0f, underlineY),
        size = size.copy(height = 1.5.dp.toPx())
    )
    
    // Box shadow effect (0px 0px 8px 3px) - glow effect
    for (i in 1..3) {
        drawRect(
            color = color.copy(alpha = 0.3f / i),
            topLeft = Offset(-i.dp.toPx(), underlineY - i.dp.toPx()),
            size = size.copy(
                width = size.width + (i * 2).dp.toPx(), 
                height = 1.5.dp.toPx() + (i * 2).dp.toPx()
            )
        )
    }
}

private fun DrawScope.drawScanningLine(color: Color, position: Float) {
    val yPos = size.height * (1f - position)
    
    // Scanning line
    drawRect(
        color = color,
        topLeft = Offset(0f, yPos),
        size = size.copy(width = size.width, height = 1.5.dp.toPx())
    )
    
    // Glow effect for scanning line
    drawRect(
        color = color.copy(alpha = 0.5f),
        topLeft = Offset(-2.dp.toPx(), yPos - 2.dp.toPx()),
        size = size.copy(width = size.width + 4.dp.toPx(), height = 5.dp.toPx())
    )
}