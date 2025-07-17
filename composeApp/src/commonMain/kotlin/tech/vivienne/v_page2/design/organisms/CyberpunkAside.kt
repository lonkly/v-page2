package tech.vivienne.v_page2.design.organisms

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.CyberpunkTheme

data class AsideMenuItem(
    val label: String,
    val onClick: () -> Unit
)

@Composable
fun CPAside(
    items: List<AsideMenuItem>,
    modifier: Modifier = Modifier,
    width: Dp = 230.dp,
    collapsedOffset: Dp = 43.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    val offsetX by animateDpAsState(
        targetValue = if (isHovered) width - 5.dp else collapsedOffset,
        animationSpec = tween(300),
        label = "aside_offset"
    )
    
    Box(
        modifier = modifier
            .offset(x = offsetX)
            .width(width)
            .hoverable(interactionSource)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items.forEach { item ->
                AsideMenuItem(
                    item = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AsideMenuItem(
    item: AsideMenuItem,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    val backgroundColor = if (isHovered) {
        CyberpunkTheme.colors.blackPrimary
    } else {
        CyberpunkTheme.colors.yellowPrimary
    }
    
    val textColor = if (isHovered) {
        CyberpunkTheme.colors.yellowPrimary
    } else {
        CyberpunkTheme.colors.blackPrimary
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationZ = -15f
            }
            .clip(AsideMenuItemShape())
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = item.onClick
            )
            .hoverable(interactionSource)
    ) {
        val borderColor = CyberpunkTheme.colors.blackPrimary
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawBorder(borderColor)
        }
        
        Text(
            text = item.label,
            style = TextStyle(
                fontSize = 21.sp,
                color = textColor
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .graphicsLayer {
                    rotationZ = 15f
                }
        )
    }
}

private fun DrawScope.drawBorder(borderColor: Color) {
    
    // Left border
    drawRect(
        color = borderColor,
        topLeft = Offset(0f, 0f),
        size = Size(10.dp.toPx(), size.height)
    )
    
    // Bottom border
    drawRect(
        color = borderColor,
        topLeft = Offset(0f, size.height - 10.dp.toPx()),
        size = Size(size.width, 10.dp.toPx())
    )
    
    // Top border
    drawRect(
        color = borderColor,
        topLeft = Offset(0f, 0f),
        size = Size(size.width, 1.dp.toPx())
    )
}

class AsideMenuItemShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(size.width * 0.4f, size.height - with(density) { 9.dp.toPx() })
            lineTo(0f, size.height)
            lineTo(size.width * 0.01f, size.height * 0.62f)
            close()
        }
        return Outline.Generic(path)
    }
}