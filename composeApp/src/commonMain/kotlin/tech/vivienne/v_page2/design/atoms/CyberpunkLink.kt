package tech.vivienne.v_page2.design.atoms

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Composable
fun CPLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    color: Color = Color.Unspecified,
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
            repeatMode = RepeatMode.Reverse
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
            style = textStyle,
            color = color,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .align(Alignment.BottomCenter)
        ) {
            if (isHovered) {
                drawScanningUnderline(underlineColor, scanPosition)
            } else {
                drawStaticUnderline(underlineColor)
            }
        }
    }
}

private fun DrawScope.drawStaticUnderline(color: Color) {
    drawRect(
        color = color,
        topLeft = Offset(0f, 0f),
        size = size.copy(height = 1.5.dp.toPx())
    )
}

private fun DrawScope.drawScanningUnderline(color: Color, position: Float) {
    val yPos = size.height * (1f - position)
    
    drawRect(
        color = color.copy(alpha = 0.3f),
        topLeft = Offset(0f, 0f),
        size = size.copy(height = 1.5.dp.toPx())
    )
    
    drawRect(
        color = color,
        topLeft = Offset(0f, yPos),
        size = size.copy(height = 1.5.dp.toPx())
    )
    
    drawRect(
        color = color.copy(alpha = 0.5f),
        topLeft = Offset(0f, yPos - 4.dp.toPx()),
        size = size.copy(height = 8.dp.toPx())
    )
}