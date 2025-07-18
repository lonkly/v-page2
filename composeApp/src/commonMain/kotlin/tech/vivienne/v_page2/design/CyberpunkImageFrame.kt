package tech.vivienne.v_page2.design

import coil3.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CyberpunkImageFrame(
    modifier: Modifier = Modifier,
    dotted: Boolean = false,
    borderColor: Color = CyberpunkTheme.colors.borderGreen,
    backgroundColor: Color = CyberpunkTheme.colors.blackPrimary,
    scanningEffect: Boolean = false,
    frameStyle: CyberpunkFrameStyle = CyberpunkFrameStyle.Standard,
    label: String? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val colors = CyberpunkTheme.colors

    val backgroundBrush = if (dotted) {
        Brush.radialGradient(
            colors = listOf(
                colors.yellowPrimary.copy(alpha = 0.3f),
                Color.Transparent
            ),
            radius = 5f
        )
    } else {
        SolidColor(backgroundColor)
    }

    val borderWidth = when (frameStyle) {
        CyberpunkFrameStyle.Standard -> 2.dp
        CyberpunkFrameStyle.Thick -> 4.dp
        CyberpunkFrameStyle.Glowing -> 3.dp
        CyberpunkFrameStyle.Minimal -> 1.dp
    }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .clip(CyberpunkShapes.ImageFrameShape)
                .background(backgroundBrush)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = CyberpunkShapes.ImageFrameShape
                )
                .drawBehind {
                    if (dotted) {
                        drawDottedPattern(colors)
                    }
                    if (frameStyle == CyberpunkFrameStyle.Glowing) {
                        drawGlowingBorder(borderColor, borderWidth.toPx())
                    }
                }
                .padding(
                    top = 30.dp,
                    start = 5.dp,
                    end = 5.dp,
                    bottom = 15.dp
                )
                .scanningEffect(
                    isActive = scanningEffect,
                    direction = ScanDirection.Horizontal,
                    color = borderColor
                ),
            content = content
        )

        label?.let { labelText ->
            Text(
                text = labelText,
                style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = borderColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.Start)
            )
        }
    }
}

@Composable
fun CyberpunkProfileFrame(
    imageUrl: String?,
    name: String,
    status: String,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 100.dp,
    isOnline: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val colors = CyberpunkTheme.colors
    val statusColor = if (isOnline) colors.greenPrimary else colors.redPrimary

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            CyberpunkImageFrame(
                modifier = Modifier.size(size),
                borderColor = statusColor,
                frameStyle = CyberpunkFrameStyle.Glowing,
                scanningEffect = isOnline
            ) {
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colors.blackPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name.take(1).uppercase(),
                            style = CyberpunkTheme.typography.headlineLarge,
                            color = colors.yellowPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Status indicator
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = statusColor,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = colors.blackPrimary,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    .align(Alignment.BottomEnd)
            )
        }

        Text(
            text = name,
            style = CyberpunkTheme.typography.bodyMedium,
            color = colors.yellowPrimary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = status,
            style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            color = statusColor,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun CyberpunkGalleryFrame(
    imageUrl: String?,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val colors = CyberpunkTheme.colors

    Column(modifier = modifier) {
        CyberpunkImageFrame(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            frameStyle = CyberpunkFrameStyle.Standard,
            borderColor = colors.borderGreen
        ) {
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.blackPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "NO IMAGE",
                        style = CyberpunkTheme.typography.bodyMedium,
                        color = colors.yellowPrimary.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Text(
            text = title,
            style = CyberpunkTheme.typography.bodyLarge,
            color = colors.yellowPrimary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = description,
            style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = colors.yellowPrimary.copy(alpha = 0.7f),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

private fun DrawScope.drawDottedPattern(colors: CyberpunkColorScheme) {
    val dotSize = 2.dp.toPx()
    val spacing = 5.dp.toPx()

    for (x in 0 until (size.width / spacing).toInt()) {
        for (y in 0 until (size.height / spacing).toInt()) {
            drawCircle(
                color = colors.yellowPrimary.copy(alpha = 0.3f),
                radius = dotSize / 2,
                center = Offset(x * spacing, y * spacing)
            )
        }
    }
}

private fun DrawScope.drawGlowingBorder(borderColor: Color, borderWidth: Float) {
    val glowRadius = borderWidth * 2
    val glowColor = borderColor.copy(alpha = 0.3f)

    // Draw multiple concentric borders for glow effect
    for (i in 1..3) {
        drawRect(
            color = glowColor.copy(alpha = 0.1f / i),
            topLeft = Offset(-glowRadius * i, -glowRadius * i),
            size = androidx.compose.ui.geometry.Size(
                size.width + glowRadius * i * 2,
                size.height + glowRadius * i * 2
            ),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = borderWidth * i)
        )
    }
}

enum class CyberpunkFrameStyle {
    Standard, Thick, Glowing, Minimal
}

@Preview
@Composable
fun CyberpunkImageFramePreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CyberpunkImageFrame(
                    modifier = Modifier.size(100.dp),
                    frameStyle = CyberpunkFrameStyle.Standard,
                    label = "Standard"
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.neonGreen)
                    )
                }

                CyberpunkImageFrame(
                    modifier = Modifier.size(100.dp),
                    frameStyle = CyberpunkFrameStyle.Standard,
                    dotted = true,
                    label = "Dotted"
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.redPrimary)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CyberpunkImageFrame(
                    modifier = Modifier.size(100.dp),
                    frameStyle = CyberpunkFrameStyle.Glowing,
                    borderColor = CyberpunkTheme.colors.bluePrimary,
                    label = "Glowing"
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.bluePrimary)
                    )
                }

                CyberpunkImageFrame(
                    modifier = Modifier.size(100.dp),
                    frameStyle = CyberpunkFrameStyle.Standard,
                    scanningEffect = true,
                    label = "Scanning"
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.orangePrimary)
                    )
                }
            }

            CyberpunkProfileFrame(
                imageUrl = null,
                name = "V",
                status = "Online",
                isOnline = true,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            CyberpunkGalleryFrame(
                imageUrl = null,
                title = "Night City",
                description = "The sprawling metropolis of the future",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkImageFrameDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CyberpunkProfileFrame(
                    imageUrl = null,
                    name = "Johnny",
                    status = "Offline",
                    isOnline = false,
                    size = 80.dp
                )

                CyberpunkProfileFrame(
                    imageUrl = null,
                    name = "Alt",
                    status = "Connected",
                    isOnline = true,
                    size = 80.dp
                )
            }

            CyberpunkGalleryFrame(
                imageUrl = null,
                title = "Arasaka Tower",
                description = "Corporate headquarters in the heart of Night City",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}