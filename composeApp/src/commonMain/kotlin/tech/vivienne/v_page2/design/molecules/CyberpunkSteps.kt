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
fun CPSteps(
    steps: List<StepItem>,
    modifier: Modifier = Modifier,
    isBlackSection: Boolean = false
) {
    val circleColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.purplePrimary
    val currentCircleColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.blackPrimary
    val textColor = if (isBlackSection) CyberpunkTheme.colors.blackPrimary else CyberpunkTheme.colors.yellowPrimary
    val currentTextColor = if (isBlackSection) CyberpunkTheme.colors.blackPrimary else CyberpunkTheme.colors.yellowPrimary
    val lineColor = if (isBlackSection) CyberpunkTheme.colors.yellowPrimary else CyberpunkTheme.colors.purplePrimary
    
    BoxWithConstraints(modifier = modifier) {
        val isCompact = maxWidth < 600.dp
        
        if (isCompact) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                steps.forEachIndexed { index, step ->
                    CompactStepItem(
                        step = step,
                        stepNumber = index + 1,
                        circleColor = if (step.isCurrent) currentCircleColor else circleColor,
                        textColor = if (step.isCurrent) currentTextColor else textColor,
                        lineColor = if (step.isCurrent) currentCircleColor else lineColor
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
    circleColor: Color,
    textColor: Color,
    lineColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp),
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
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )
        }
        
        Text(
            text = step.label,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = CyberpunkTheme.colors.blackPrimary
            ),
            modifier = Modifier.padding(bottom = 5.dp)
        )
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .padding(end = if (isLast) 0.dp else 20.dp)
    ) {
        Text(
            text = step.label,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = CyberpunkTheme.colors.blackPrimary
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 65.dp, end = 20.dp)
        )
        
        Box(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomStart)
                .offset(y = 40.dp)
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber.toString(),
                style = TextStyle(
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
                    .offset(x = 40.dp, y = 20.dp)
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