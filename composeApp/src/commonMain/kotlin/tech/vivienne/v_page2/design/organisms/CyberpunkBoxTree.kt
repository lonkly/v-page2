package tech.vivienne.v_page2.design.organisms

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.CyberpunkTheme

enum class BoxTreeNodeStatus {
    NORMAL,
    RESOLVED,
    PENDING
}

enum class BoxTreeNodeWidth {
    FULL,    // width1 - 100%
    HALF,    // default - 50%
    QUARTER  // width3 - 25%
}

data class BoxTreeNode(
    val content: @Composable BoxScope.() -> Unit,
    val status: BoxTreeNodeStatus = BoxTreeNodeStatus.NORMAL,
    val width: BoxTreeNodeWidth = BoxTreeNodeWidth.HALF,
    val childrenCount: Int = 1
)

@Composable
fun CyberPunkBoxTree(
    nodes: List<BoxTreeNode>,
    modifier: Modifier = Modifier,
    isBlackSection: Boolean = false
) {
    BoxWithConstraints(modifier = modifier) {
        val isCompact = maxWidth < 600.dp
        val isMedium = maxWidth < 900.dp
        
        val gap = when {
            isCompact -> 15.dp
            isMedium -> 20.dp
            else -> 30.dp
        }
        
        val borderHeight = when {
            isCompact -> 1.dp
            isMedium -> 3.dp
            else -> 5.dp
        }
        
        val padding = when {
            isCompact -> 5.dp
            isMedium -> 10.dp
            else -> 20.dp
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (isMedium) 0.dp else 50.dp),
            horizontalArrangement = Arrangement.spacedBy(gap)
        ) {
            nodes.forEach { node ->
                BoxTreeNodeItem(
                    node = node,
                    gap = gap,
                    borderHeight = borderHeight,
                    padding = padding,
                    isBlackSection = isBlackSection,
                    showLabels = !isMedium,
                    modifier = when (node.width) {
                        BoxTreeNodeWidth.FULL -> Modifier.weight(1f)
                        BoxTreeNodeWidth.HALF -> Modifier.weight(0.5f)
                        BoxTreeNodeWidth.QUARTER -> Modifier.weight(0.25f)
                    }
                )
            }
        }
    }
}

@Composable
private fun BoxTreeNodeItem(
    node: BoxTreeNode,
    gap: Dp,
    borderHeight: Dp,
    padding: Dp,
    isBlackSection: Boolean,
    showLabels: Boolean,
    modifier: Modifier = Modifier
) {
    val (borderColor, textColor, connectionColor) = when (node.status) {
        BoxTreeNodeStatus.NORMAL -> Triple(
            if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.bluePrimary,
            if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.bluePrimary,
            if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.bluePrimary
        )
        BoxTreeNodeStatus.RESOLVED -> Triple(
            if (isBlackSection) CyberpunkTheme.colors.neonGreen else CyberpunkTheme.colors.greenPrimary,
            if (isBlackSection) CyberpunkTheme.colors.neonGreen else CyberpunkTheme.colors.greenPrimary,
            if (isBlackSection) CyberpunkTheme.colors.neonGreen else CyberpunkTheme.colors.greenPrimary
        )
        BoxTreeNodeStatus.PENDING -> Triple(
            CyberpunkTheme.colors.purplePrimary,
            CyberpunkTheme.colors.purplePrimary,
            CyberpunkTheme.colors.purplePrimary
        )
    }
    
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = borderHeight,
                    color = borderColor,
                    shape = RectangleShape
                )
                .padding(padding)
        ) {
            node.content(this)
            
            if (showLabels && node.status != BoxTreeNodeStatus.NORMAL) {
                val label = when (node.status) {
                    BoxTreeNodeStatus.RESOLVED -> "RESOLVED"
                    BoxTreeNodeStatus.PENDING -> "NO ACCESS"
                    else -> ""
                }
                
                Text(
                    text = label,
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = if (node.width == BoxTreeNodeWidth.QUARTER) 40.sp else 75.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor.copy(alpha = 0.7f)
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = -15f
                        }
                )
            }
        }
        
        // Connection lines
        if (node.childrenCount > 0) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(gap)
                    .align(Alignment.BottomCenter)
                    .offset(y = gap + borderHeight)
            ) {
                drawConnectionLines(node.childrenCount, connectionColor)
            }
        }
    }
}

private fun DrawScope.drawConnectionLines(childrenCount: Int, color: Color) {
    when (childrenCount) {
        1 -> {
            // Single line in the center
            val path = Path().apply {
                moveTo(size.width * 0.5f - 2.dp.toPx(), 0f)
                lineTo(size.width * 0.5f - 2.dp.toPx(), size.height)
                lineTo(size.width * 0.5f + 2.dp.toPx(), size.height)
                lineTo(size.width * 0.5f + 2.dp.toPx(), 0f)
                close()
            }
            clipPath(path) {
                drawRect(color)
            }
        }
        2 -> {
            // Two lines at 25% and 75%
            val path = Path().apply {
                // First line
                moveTo(size.width * 0.25f - 2.dp.toPx(), 0f)
                lineTo(size.width * 0.25f - 2.dp.toPx(), size.height)
                lineTo(size.width * 0.25f + 2.dp.toPx(), size.height)
                lineTo(size.width * 0.25f + 2.dp.toPx(), 0f)
                // Second line
                moveTo(size.width * 0.75f - 2.dp.toPx(), 0f)
                lineTo(size.width * 0.75f - 2.dp.toPx(), size.height)
                lineTo(size.width * 0.75f + 2.dp.toPx(), size.height)
                lineTo(size.width * 0.75f + 2.dp.toPx(), 0f)
                close()
            }
            clipPath(path) {
                drawRect(color)
            }
        }
        else -> {
            // No lines for 0 children
        }
    }
}