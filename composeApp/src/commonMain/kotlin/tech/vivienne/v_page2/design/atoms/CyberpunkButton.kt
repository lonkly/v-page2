package tech.vivienne.v_page2.design.atoms


import tech.vivienne.v_page2.design.CyberpunkShapes
import tech.vivienne.v_page2.design.CyberpunkTheme
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.glitchEffect

@Composable
fun CyberpunkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: CyberpunkButtonVariant = CyberpunkButtonVariant.Red,
    glitchEffect: Boolean = false,
    text: String,
    codeIndicator: String = "R-25"
) {
    val colors = CyberpunkTheme.colors
    val backgroundColor = when (variant) {
        CyberpunkButtonVariant.Red -> colors.redPrimary
        CyberpunkButtonVariant.Green -> colors.greenPrimary
        CyberpunkButtonVariant.Blue -> colors.bluePrimary
        CyberpunkButtonVariant.Purple -> colors.purplePrimary
    }

    var isPressed by remember { mutableStateOf(false) }

    val pressedScale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100)
    )

    Box(
        modifier = modifier
            .clip(CyberpunkShapes.ButtonShape)
            .background(backgroundColor)
            .clickable(enabled = enabled) {
                isPressed = !isPressed
                onClick()
            }
            .glitchEffect(isActive = glitchEffect, intensity = 2.5f)
    ) {
        // Main button content with padding
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text.uppercase(),
                style = CyberpunkTheme.typography.labelLarge.copy(
                    fontSize = 18.sp, // 1.5rem
                    lineHeight = 18.sp, // 1.5rem
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight(600)
                ),
                color = colors.white
            )
        }
        
        // Right border
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .width(3.dp)
                .background(colors.borderGreen)
        )

        // Button code indicator (outside for non-primary variants)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-12).dp)
                    .background(colors.yellowPrimary)
                    .padding(horizontal = 2.dp, vertical = 0.dp)
            ) {
                Text(
                    text = codeIndicator,
                    style = CyberpunkTheme.typography.bodyMedium.copy(
                        fontSize = 7.2.sp, // 0.6rem
                        lineHeight = 7.2.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colors.blackPrimary
                )
                
                // Left border of indicator
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = (-2).dp)
                        .fillMaxHeight()
                        .width(2.dp)
                        .background(colors.borderGreen)
                )
        }
    }
}

enum class CyberpunkButtonVariant {
    Red, Green, Blue, Purple
}

