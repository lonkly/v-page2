package tech.vivienne.v_page2.design.molecules

import tech.vivienne.v_page2.design.CyberpunkShapes
import tech.vivienne.v_page2.design.CyberpunkTheme
import tech.vivienne.v_page2.design.ScanDirection
import tech.vivienne.v_page2.design.glitchEffect
import tech.vivienne.v_page2.design.scanningEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CyberpunkListItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    glitchEffect: Boolean = false,
    scanningEffect: Boolean = false,
    enabled: Boolean = true,
    variant: CyberpunkListItemVariant = CyberpunkListItemVariant.Default,
    statusIndicator: CyberpunkListItemStatus? = null
) {
    val colors = CyberpunkTheme.colors

    val backgroundColor = when (variant) {
        CyberpunkListItemVariant.Default -> colors.blackPrimary
        CyberpunkListItemVariant.Highlighted -> colors.borderGreen.copy(alpha = 0.1f)
        CyberpunkListItemVariant.Warning -> colors.orangePrimary.copy(alpha = 0.1f)
        CyberpunkListItemVariant.Error -> colors.redPrimary.copy(alpha = 0.1f)
        CyberpunkListItemVariant.Success -> colors.greenPrimary.copy(alpha = 0.1f)
    }

    val borderColor = when (variant) {
        CyberpunkListItemVariant.Default -> colors.borderGreen
        CyberpunkListItemVariant.Highlighted -> colors.borderGreen
        CyberpunkListItemVariant.Warning -> colors.orangePrimary
        CyberpunkListItemVariant.Error -> colors.redPrimary
        CyberpunkListItemVariant.Success -> colors.greenPrimary
    }

    val contentColor = when (variant) {
        CyberpunkListItemVariant.Default -> colors.yellowPrimary
        CyberpunkListItemVariant.Highlighted -> colors.yellowPrimary
        CyberpunkListItemVariant.Warning -> colors.orangePrimary
        CyberpunkListItemVariant.Error -> colors.redPrimary
        CyberpunkListItemVariant.Success -> colors.greenPrimary
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { onClick() }
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = if (enabled) borderColor else borderColor.copy(alpha = 0.5f),
                shape = CyberpunkShapes.InputFieldShape
            )
            .clip(CyberpunkShapes.InputFieldShape)
            .padding(16.dp)
            .glitchEffect(isActive = glitchEffect && enabled, intensity = 0.5f)
            .scanningEffect(
                isActive = scanningEffect && enabled,
                direction = ScanDirection.Horizontal,
                color = borderColor
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Status indicator
        statusIndicator?.let { status ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = when (status) {
                            CyberpunkListItemStatus.Online -> colors.greenPrimary
                            CyberpunkListItemStatus.Offline -> colors.redPrimary
                            CyberpunkListItemStatus.Warning -> colors.orangePrimary
                            CyberpunkListItemStatus.Processing -> colors.bluePrimary
                            CyberpunkListItemStatus.Unknown -> colors.yellowPrimary
                        },
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(12.dp))
        }

        // Leading content
        leadingContent?.let { content ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = contentColor.copy(alpha = 0.1f),
                        shape = CyberpunkShapes.InputFieldShape
                    )
                    .border(
                        width = 1.dp,
                        color = contentColor,
                        shape = CyberpunkShapes.InputFieldShape
                    )
                    .padding(8.dp)
            ) {
                content()
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

        // Content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = CyberpunkTheme.typography.bodyLarge,
                color = if (enabled) contentColor else contentColor.copy(alpha = 0.5f),
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            subtitle?.let { sub ->
                Text(
                    text = sub,
                    style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = if (enabled) contentColor.copy(alpha = 0.7f) else contentColor.copy(alpha = 0.3f),
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        // Trailing content
        trailingContent?.let { content ->
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun CyberpunkListItemWithBadge(
    title: String,
    onClick: () -> Unit,
    badgeText: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    badgeColor: Color = CyberpunkTheme.colors.redPrimary,
    variant: CyberpunkListItemVariant = CyberpunkListItemVariant.Default
) {
    CyberpunkListItem(
        title = title,
        onClick = onClick,
        modifier = modifier,
        subtitle = subtitle,
        leadingContent = leadingContent,
        variant = variant,
        trailingContent = {
            Box(
                modifier = Modifier
                    .background(
                        color = badgeColor,
                        shape = CyberpunkShapes.ButtonShape
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = badgeText,
                    style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = CyberpunkTheme.colors.white,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

enum class CyberpunkListItemVariant {
    Default, Highlighted, Warning, Error, Success
}

enum class CyberpunkListItemStatus {
    Online, Offline, Warning, Processing, Unknown
}

@Preview
@Composable
fun CyberpunkListItemPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkListItem(
                title = "Neural Network Connection",
                subtitle = "Status: Active • Latency: 45ms",
                onClick = {},
                statusIndicator = CyberpunkListItemStatus.Online,
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.neonGreen)
                    )
                },
                trailingContent = {
                    Text(
                        text = "98%",
                        style = CyberpunkTheme.typography.bodyMedium,
                        color = CyberpunkTheme.colors.neonGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            )

            CyberpunkListItem(
                title = "Firewall Breach",
                subtitle = "Attempting to bypass security protocols",
                onClick = {},
                variant = CyberpunkListItemVariant.Warning,
                statusIndicator = CyberpunkListItemStatus.Processing,
                glitchEffect = true
            )

            CyberpunkListItem(
                title = "System Error",
                subtitle = "Critical failure in main database",
                onClick = {},
                variant = CyberpunkListItemVariant.Error,
                statusIndicator = CyberpunkListItemStatus.Offline,
                scanningEffect = true
            )

            CyberpunkListItem(
                title = "Data Upload Complete",
                subtitle = "Successfully transferred 2.4 GB",
                onClick = {},
                variant = CyberpunkListItemVariant.Success,
                statusIndicator = CyberpunkListItemStatus.Online
            )

            CyberpunkListItemWithBadge(
                title = "Security Alert",
                subtitle = "Unauthorized access detected",
                badgeText = "NEW",
                onClick = {},
                variant = CyberpunkListItemVariant.Highlighted,
                badgeColor = CyberpunkTheme.colors.redPrimary
            )

            CyberpunkListItem(
                title = "Disabled Connection",
                subtitle = "Service temporarily unavailable",
                onClick = {},
                enabled = false,
                statusIndicator = CyberpunkListItemStatus.Unknown
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkListItemDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkListItem(
                title = "Hack in Progress",
                subtitle = "Breaking encryption layers",
                onClick = {},
                variant = CyberpunkListItemVariant.Warning,
                glitchEffect = true,
                statusIndicator = CyberpunkListItemStatus.Processing
            )

            CyberpunkListItem(
                title = "Target Acquired",
                subtitle = "Mainframe access established",
                onClick = {},
                variant = CyberpunkListItemVariant.Success,
                statusIndicator = CyberpunkListItemStatus.Online,
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.greenPrimary)
                    )
                }
            )

            CyberpunkListItemWithBadge(
                title = "Critical Alert",
                subtitle = "ICE detected in the system",
                badgeText = "URGENT",
                onClick = {},
                variant = CyberpunkListItemVariant.Error,
                badgeColor = CyberpunkTheme.colors.redPrimary
            )
        }
    }
}