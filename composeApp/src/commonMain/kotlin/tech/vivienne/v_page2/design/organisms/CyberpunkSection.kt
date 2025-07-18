package tech.vivienne.v_page2.design.organisms

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

enum class SectionStyle {
    YELLOW,
    BLACK
}

enum class SectionBorder {
    BOTTOM,
    BOTH
}

@Composable
fun CyberPunkSection(
    modifier: Modifier = Modifier,
    style: SectionStyle = SectionStyle.YELLOW,
    border: SectionBorder = SectionBorder.BOTTOM,
    isDotted: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val backgroundColor = when (style) {
        SectionStyle.YELLOW -> CyberpunkTheme.colors.yellowPrimary
        SectionStyle.BLACK -> CyberpunkTheme.colors.blackPrimary
    }
    
    val borderColor = when (style) {
        SectionStyle.YELLOW -> CyberpunkTheme.colors.blackPrimary
        SectionStyle.BLACK -> CyberpunkTheme.colors.yellowPrimary
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .align(Alignment.BottomCenter)
        ) {
            when (border) {
                SectionBorder.BOTTOM -> drawBottomBorder(borderColor)
                SectionBorder.BOTH -> drawBothBorders(borderColor)
            }
        }
        
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .align(Alignment.TopCenter)
        ) {
            drawTopDecoration(borderColor)
        }
        
        if (isDotted) {
            val dotColor = when (style) {
                SectionStyle.YELLOW -> CyberpunkTheme.colors.blackPrimary.copy(alpha = 0.26f)
                SectionStyle.BLACK -> CyberpunkTheme.colors.yellowPrimary.copy(alpha = 0.13f)
            }
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                drawDottedPattern(dotColor)
            }
        }
        
        content()
    }
}

private fun DrawScope.drawBottomBorder(color: Color) {
    val path = Path().apply {
        moveTo(0f, 0f)
        lineTo(size.width * 0.22f, 0f)
        lineTo(size.width * 0.22f + 30.dp.toPx(), 30.dp.toPx())
        lineTo(size.width * 0.52f, 30.dp.toPx())
        lineTo(size.width * 0.52f + 15.dp.toPx(), 15.dp.toPx())
        lineTo(size.width * 0.7f, 15.dp.toPx())
        lineTo(size.width * 0.7f + 15.dp.toPx(), 30.dp.toPx())
        lineTo(size.width * 0.9f, 30.dp.toPx())
        lineTo(size.width * 0.9f + 30.dp.toPx(), 0f)
        lineTo(size.width, 0f)
        lineTo(size.width, 30.dp.toPx())
        lineTo(0f, 30.dp.toPx())
        close()
    }
    
    clipPath(path) {
        drawRect(color)
    }
}

private fun DrawScope.drawBothBorders(color: Color) {
    val path = Path().apply {
        moveTo(0f, size.height * 0.3f)
        lineTo(30.dp.toPx(), size.height * 0.3f + 30.dp.toPx())
        lineTo(30.dp.toPx(), size.height - 30.dp.toPx())
        lineTo(size.width * 0.22f, size.height - 30.dp.toPx())
        lineTo(size.width * 0.22f + 30.dp.toPx(), size.height)
        lineTo(size.width * 0.52f, size.height)
        lineTo(size.width * 0.52f + 15.dp.toPx(), size.height - 15.dp.toPx())
        lineTo(size.width * 0.7f, size.height - 15.dp.toPx())
        lineTo(size.width * 0.7f + 15.dp.toPx(), size.height)
        lineTo(size.width * 0.9f, size.height)
        lineTo(size.width * 0.9f + 30.dp.toPx(), size.height - 30.dp.toPx())
        lineTo(size.width, size.height - 30.dp.toPx())
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    
    clipPath(path) {
        drawRect(color)
    }
}

private fun DrawScope.drawTopDecoration(color: Color) {
    val path = Path().apply {
        moveTo(size.width * 0.22f + 28.dp.toPx(), -1.dp.toPx())
        lineTo(size.width * 0.22f + 45.dp.toPx(), 15.dp.toPx())
        lineTo(size.width * 0.34f, 15.dp.toPx())
        lineTo(size.width * 0.34f + 15.dp.toPx(), -1.dp.toPx())
        lineTo(size.width * 0.7f, 0f)
        close()
    }
    
    clipPath(path) {
        drawRect(color)
    }
}

private fun DrawScope.drawDottedPattern(dotColor: Color) {
    
    val dotRadius = 1.dp.toPx()
    val spacing = 5.dp.toPx()
    
    for (x in 0 until size.width.toInt() step spacing.toInt()) {
        for (y in 0 until size.height.toInt() step spacing.toInt()) {
            drawCircle(
                color = dotColor,
                radius = dotRadius,
                center = Offset(x.toFloat() - 13.dp.toPx(), y.toFloat() - 3.dp.toPx())
            )
        }
    }
}