package tech.vivienne.v_page2.design.molecules

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.CyberpunkTheme
import kotlin.random.Random

enum class ListType {
    UNORDERED,
    ORDERED
}

@Composable
fun CPList(
    items: List<String>,
    modifier: Modifier = Modifier,
    listType: ListType = ListType.UNORDERED,
    isGlitched: Boolean = false,
    textStyle: TextStyle = TextStyle(fontSize = 19.sp)
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            CPListItem(
                text = item,
                index = index + 1,
                listType = listType,
                isGlitched = isGlitched && shouldGlitch(index),
                textStyle = textStyle,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun CPListItem(
    text: String,
    index: Int,
    listType: ListType,
    isGlitched: Boolean,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    
    val skew by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = getGlitchDuration(index)
                0f at 0 using LinearEasing
                -3f at (durationMillis * 0.1f).toInt() using LinearEasing
                3f at (durationMillis * 0.11f).toInt() using LinearEasing
                0f at (durationMillis * 0.5f).toInt() using LinearEasing
                3f at (durationMillis * 0.51f).toInt() using LinearEasing
                -3f at (durationMillis * 0.59f).toInt() using LinearEasing
                0f at (durationMillis * 0.6f).toInt() using LinearEasing
            }
        )
    )
    
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = getGlitchDuration(index)
                0f at 0 using LinearEasing
                -2f at (durationMillis * 0.1f).toInt() using LinearEasing
                2f at (durationMillis * 0.11f).toInt() using LinearEasing
                0f at (durationMillis * 0.5f).toInt() using LinearEasing
                5f at (durationMillis * 0.51f).toInt() using LinearEasing
                5f at (durationMillis * 0.59f).toInt() using LinearEasing
                0f at (durationMillis * 0.6f).toInt() using LinearEasing
            }
        )
    )
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .offset(x = if (isGlitched) offset.dp else 0.dp),
        verticalAlignment = Alignment.Top
    ) {
        val bulletColor = CyberpunkTheme.colors.blackPrimary
        val numberColor = CyberpunkTheme.colors.yellowPrimary
        
        Box(
            modifier = Modifier
                .size(19.dp)
                .offset(x = (-29).dp)
        ) {
            when (listType) {
                ListType.UNORDERED -> {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawUnorderedBullet(bulletColor)
                    }
                }
                ListType.ORDERED -> {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawOrderedBullet(index, bulletColor)
                    }
                    Text(
                        text = index.toString(),
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = numberColor,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier
                .graphicsLayer {
                    if (isGlitched) {
                        rotationZ = skew
                    }
                }
        )
    }
}
private fun DrawScope.drawUnorderedBullet(color: Color) {
    val path = Path().apply {
        moveTo(0f, size.height * 0.1f)
        lineTo(size.width, size.height * 0.7f)
        lineTo(size.width * 0.4f, size.height)
        lineTo(size.width * 0.5f, size.height * 0.7f)
        close()
    }
    
    clipPath(path) {
        drawRect(color)
    }
}

private fun DrawScope.drawOrderedBullet(number: Int, color: Color) {
    val path = Path().apply {
        moveTo(0f, 0f)
        lineTo(size.width * 0.6f, 0f)
        lineTo(size.width, size.height * 0.4f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    
    clipPath(path) {
        drawRect(color)
    }
}

private fun shouldGlitch(index: Int): Boolean {
    return when {
        index % 5 == 4 -> true
        index % 3 == 2 -> true
        index % 2 == 1 -> true
        index == 0 -> true
        else -> false
    }
}

private fun getGlitchDuration(index: Int): Int {
    return when {
        index % 5 == 4 -> 630
        index % 3 == 2 -> 450
        index % 2 == 1 -> 1350
        index % 4 == 3 -> 1080
        else -> 900
    }
}