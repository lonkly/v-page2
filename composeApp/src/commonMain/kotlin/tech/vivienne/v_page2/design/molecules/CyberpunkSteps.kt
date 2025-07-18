package tech.vivienne.v_page2.design.molecules

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.CyberpunkTheme

data class StepItem(
    val label: String,
    val isCurrent: Boolean = false
)

@Composable
fun CyberPunkSteps(
    steps: List<StepItem>,
    modifier: Modifier = Modifier,
    isBlackSection: Boolean = false,
    labelTextStyle: TextStyle? = null,
    numberTextStyle: TextStyle? = null
) {
    val circleColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.purplePrimary
    val currentCircleColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.blackPrimary
    val textColor = if (isBlackSection) CyberpunkTheme.colors.blackPrimary else CyberpunkTheme.colors.yellowPrimary
    val currentTextColor = if (isBlackSection) CyberpunkTheme.colors.blackPrimary else CyberpunkTheme.colors.yellowPrimary
    val lineColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.purplePrimary
    val labelColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.blackPrimary
    
    BoxWithConstraints(modifier = modifier) {
        val isCompact = maxWidth < 600.dp
        
        if (isCompact) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                steps.forEachIndexed { index, step ->
                    CompactStepItem(
                        step = step,
                        stepNumber = index + 1,
                        isLast = index == steps.lastIndex,
                        circleColor = if (step.isCurrent) currentCircleColor else circleColor,
                        textColor = if (step.isCurrent) currentTextColor else textColor,
                        lineColor = if (step.isCurrent) currentCircleColor else lineColor,
                        labelColor = labelColor,
                        labelTextStyle = labelTextStyle,
                        numberTextStyle = numberTextStyle
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                steps.forEachIndexed { index, step ->
                    FullStepItem(
                        step = step,
                        stepNumber = index + 1,
                        isLast = index == steps.lastIndex,
                        circleColor = if (step.isCurrent) currentCircleColor else circleColor,
                        textColor = if (step.isCurrent) currentTextColor else textColor,
                        lineColor = if (step.isCurrent) currentCircleColor else lineColor,
                        labelColor = labelColor,
                        labelTextStyle = labelTextStyle,
                        numberTextStyle = numberTextStyle,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun CompactStepItem(
    step: StepItem,
    stepNumber: Int,
    isLast: Boolean,
    circleColor: Color,
    textColor: Color,
    lineColor: Color,
    labelColor: Color,
    labelTextStyle: TextStyle? = null,
    numberTextStyle: TextStyle? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp)
    ) {
        // Draw vertical line connecting to next item
        if (!isLast) {
            Canvas(
                modifier = Modifier
                    .width(2.dp)
                    .height(60.dp)
                    .offset(x = (-30).dp, y = 40.dp)
            ) {
                drawRect(
                    color = lineColor,
                    size = size
                )
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (-50).dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber.toString(),
                    style = numberTextStyle ?: TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                )
            }
            
            Text(
                text = step.label,
                style = labelTextStyle ?: TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = labelColor
                ),
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
    }
}

@Composable
private fun FullStepItem(
    step: StepItem,
    stepNumber: Int,
    isLast: Boolean,
    circleColor: Color,
    textColor: Color,
    lineColor: Color,
    labelColor: Color,
    labelTextStyle: TextStyle? = null,
    numberTextStyle: TextStyle? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .padding(end = if (isLast) 0.dp else 20.dp)
    ) {
        Text(
            text = step.label,
            style = labelTextStyle ?: TextStyle(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = labelColor
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 32.dp, end = 20.dp)
        )
        
        Box(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomStart)
                .offset(y = 20.dp)
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber.toString(),
                style = numberTextStyle ?: TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            )
        }
        
        if (!isLast) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = 40.dp, y = 0.dp)
            ) {
                drawLine(lineColor)
            }
        }
    }
}

private fun DrawScope.drawLine(color: Color) {
    drawRect(
        color = color,
        topLeft = Offset(0f, 0f),
        size = size
    )
}