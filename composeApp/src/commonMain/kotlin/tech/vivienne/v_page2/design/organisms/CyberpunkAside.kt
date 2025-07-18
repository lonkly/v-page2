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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
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
fun CyberPunkAside(
    items: List<AsideMenuItem>,
    modifier: Modifier = Modifier,
    width: Dp = 230.dp,
    collapsedOffset: Dp = 43.dp,
    itemHeight: Dp? = null,
    isMobile: Boolean = false,
    onExpandedChange: ((Boolean) -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    var internalExpanded by remember { mutableStateOf(false) }
    
    // On mobile, use tap to expand/collapse, on desktop use hover
    val shouldExpand = if (isMobile) internalExpanded else isHovered
    
    val offsetX by animateDpAsState(
        targetValue = if (shouldExpand) 20.dp else width - collapsedOffset,
        animationSpec = tween(300),
        label = "aside_offset"
    )
    
    Box(
        modifier = modifier
            .offset(x = offsetX)
            .width(width)
            .graphicsLayer {
                // Ensure the menu is above other content
                this.shadowElevation = 8.dp.toPx()
                this.clip = false
            }
            .then(
                if (isMobile) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        // Toggle expansion on mobile
                        internalExpanded = !internalExpanded
                        onExpandedChange?.invoke(internalExpanded)
                    }
                } else {
                    Modifier.hoverable(interactionSource)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items.forEach { item ->
                AsideMenuItem(
                    item = item,
                    modifier = Modifier.fillMaxWidth().then(
                        if (itemHeight != null) Modifier.height(itemHeight) else Modifier
                    ),
                    isParentExpanded = shouldExpand,
                    isMobile = isMobile,
                    onItemClick = {
                        item.onClick()
                        if (isMobile) {
                            internalExpanded = false
                            onExpandedChange?.invoke(false)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun AsideMenuItem(
    item: AsideMenuItem,
    modifier: Modifier = Modifier,
    isParentExpanded: Boolean = false,
    isMobile: Boolean = false,
    onItemClick: () -> Unit = { item.onClick() }
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
                // Apply shadow after rotation so it rotates with the element
                shadowElevation = 4.dp.toPx()
                clip = true
            }
            .clip(AsideMenuItemShape())
            .background(backgroundColor)
            .then(
                // Always make clickable on desktop, only restrict on mobile when collapsed
                Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = !isMobile || isParentExpanded,
                    onClick = {
                        onItemClick()
                    }
                )
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