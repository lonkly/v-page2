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
            .border(
                width = 3.dp,
                color = colors.borderGreen,
                shape = RectangleShape
            )
            .clickable(enabled = enabled) {
                isPressed = !isPressed
                onClick()
            }
            .padding(horizontal = 75.dp, vertical = 35.dp)
            .glitchEffect(isActive = glitchEffect, intensity = 0.8f)
    ) {
        Text(
            text = text.uppercase(),
            style = CyberpunkTheme.typography.labelLarge,
            color = colors.white,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )

        // Button code indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-25).dp, y = (-2).dp)
                .background(colors.yellowPrimary)
                .border(
                    width = 2.dp,
                    color = colors.borderGreen,
                    shape = RectangleShape
                )
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Text(
                text = codeIndicator,
                style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 9.sp),
                color = colors.blackPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

enum class CyberpunkButtonVariant {
    Red, Green, Blue, Purple
}

