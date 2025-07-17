package tech.vivienne.v_page2.design.molecules

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.CyberpunkTheme

enum class ParagraphStyle {
    NORMAL,
    INVERSE
}

enum class ScanEffect {
    NONE,
    HORIZONTAL,
    VERTICAL
}

@Composable
fun CPParagraph(
    text: String,
    modifier: Modifier = Modifier,
    style: ParagraphStyle = ParagraphStyle.NORMAL,
    scanEffect: ScanEffect = ScanEffect.NONE,
    label: String = if (style == ParagraphStyle.NORMAL) "P-14" else "T-71",
    isBlackSection: Boolean = false
) {
    val backgroundColor = when {
        isBlackSection && style == ParagraphStyle.NORMAL -> CyberpunkTheme.colors.blackPrimary
        isBlackSection && style == ParagraphStyle.INVERSE -> CyberpunkTheme.colors.yellowPrimary
        style == ParagraphStyle.NORMAL -> CyberpunkTheme.colors.yellowPrimary
        else -> CyberpunkTheme.colors.blackPrimary
    }
    
    val textColor = when {
        isBlackSection && style == ParagraphStyle.NORMAL -> CyberpunkTheme.colors.yellowPrimary
        isBlackSection && style == ParagraphStyle.INVERSE -> CyberpunkTheme.colors.blackPrimary
        style == ParagraphStyle.NORMAL -> CyberpunkTheme.colors.blackPrimary
        else -> CyberpunkTheme.colors.yellowPrimary
    }
    
    val borderColor = when {
        isBlackSection && style == ParagraphStyle.NORMAL -> CyberpunkTheme.colors.yellowPrimary
        style == ParagraphStyle.NORMAL -> CyberpunkTheme.colors.blackPrimary
        else -> CyberpunkTheme.colors.borderGreen
    }
    
    val infiniteTransition = rememberInfiniteTransition()
    val scanPosition = if (scanEffect != ScanEffect.NONE) {
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = if (scanEffect == ScanEffect.VERTICAL) 9000 else 3000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        ).value
    } else 0f
    
    Box(
        modifier = modifier
            .clip(ParagraphShape())
            .then(
                if (style == ParagraphStyle.NORMAL) {
                    Modifier.border(
                        BorderStroke(
                            width = 5.dp,
                            color = borderColor
                        ),
                        ParagraphShape()
                    )
                } else {
                    Modifier.border(
                        BorderStroke(
                            width = 3.dp,
                            color = CyberpunkTheme.colors.borderGreen
                        ),
                        ParagraphShape()
                    )
                }
            )
            .background(backgroundColor)
            .padding(
                start = if (style == ParagraphStyle.NORMAL) 5.dp else 15.dp,
                end = if (style == ParagraphStyle.NORMAL) 5.dp else 15.dp,
                top = if (style == ParagraphStyle.NORMAL) 30.dp else 40.dp,
                bottom = if (style == ParagraphStyle.NORMAL) 24.dp else 30.dp
            )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 19.sp,
                fontFamily = FontFamily.Default,
                color = textColor
            )
        )
        
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-25).dp, y = 36.dp)
                .background(backgroundColor)
                .border(
                    BorderStroke(2.dp, CyberpunkTheme.colors.borderGreen),
                    RectangleShape
                )
                .padding(horizontal = 2.dp)
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                ),
                lineHeight = 10.sp
            )
        }
        
        if (scanEffect != ScanEffect.NONE) {
            val scanColor = CyberpunkTheme.colors.borderGreen
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                when (scanEffect) {
                    ScanEffect.HORIZONTAL -> drawHorizontalScan(scanPosition, scanColor)
                    ScanEffect.VERTICAL -> drawVerticalScan(scanPosition, scanColor)
                    ScanEffect.NONE -> {}
                }
            }
        }
    }
}

private fun DrawScope.drawHorizontalScan(position: Float, scanColor: Color) {
    val yPos = size.height * position
    
    drawRect(
        color = scanColor,
        topLeft = Offset(0f, yPos - 1.5.dp.toPx()),
        size = Size(size.width, 3.dp.toPx())
    )
    
    drawRect(
        color = scanColor.copy(alpha = 0.3f),
        topLeft = Offset(0f, yPos - 5.dp.toPx()),
        size = Size(size.width, 10.dp.toPx())
    )
}

private fun DrawScope.drawVerticalScan(position: Float, scanColor: Color) {
    val xPos = size.width * position
    
    drawRect(
        color = scanColor,
        topLeft = Offset(xPos - 1.5.dp.toPx(), 0f),
        size = Size(3.dp.toPx(), size.height)
    )
    
    drawRect(
        color = scanColor.copy(alpha = 0.3f),
        topLeft = Offset(xPos - 5.dp.toPx(), 0f),
        size = Size(10.dp.toPx(), size.height)
    )
}

class ParagraphShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, size.height * 0.1f)
            lineTo(size.width * 0.1f, 0f)
            lineTo(size.width * 0.6f - size.height * 0.1f, 0f)
            lineTo(size.width * 0.6f, size.height * 0.1f)
            lineTo(size.width, size.height * 0.1f)
            lineTo(size.width, size.height * 0.9f)
            lineTo(size.width * 0.95f, size.height * 0.9f)
            lineTo(size.width * 0.8f - size.height * 0.04f, size.height * 0.9f)
            lineTo(size.width * 0.8f - size.height * 0.06f, size.height)
            lineTo(size.width * 0.25f, size.height)
            lineTo(size.width * 0.2f, size.height * 0.94f)
            lineTo(0f, size.height * 0.94f)
            close()
        }
        return Outline.Generic(path)
    }
}