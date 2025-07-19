package tech.vivienne.v_page2.design.molecules

import tech.vivienne.v_page2.design.atoms.CyberpunkButton
import tech.vivienne.v_page2.design.atoms.CyberpunkButtonVariant
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.*

@Composable
fun CyberpunkCard(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = false,
    elevated: Boolean = false,
    clickable: Boolean = false,
    onClick: (() -> Unit)? = null,
    scanningEffect: Boolean = false,
    headerContent: @Composable (RowScope.() -> Unit)? = null,
    footerContent: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = CyberpunkTheme.colors
    val backgroundColor = if (darkVariant) colors.blackPrimary else colors.yellowPrimary
    val contentColor = if (darkVariant) colors.yellowPrimary else colors.blackPrimary
    val borderColor = colors.borderGreen

    Card(
        modifier = modifier
            .let { mod ->
                if (clickable && onClick != null) {
                    mod.clickable { onClick() }
                } else {
                    mod
                }
            }
            .scanningEffect(
                isActive = scanningEffect,
                direction = ScanDirection.Horizontal,
                color = borderColor
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (elevated) 8.dp else 0.dp
        ),
        shape = RectangleShape,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Column {
            // Header
            headerContent?.let { header ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (darkVariant) colors.yellowPrimary.copy(alpha = 0.1f)
                            else colors.blackPrimary.copy(alpha = 0.1f)
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    header()
                }
            }

            // Content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides contentColor,
                    LocalTextStyle provides CyberpunkTheme.typography.bodyMedium
                ) {
                    content()
                }
            }

            // Footer
            footerContent?.let { footer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (darkVariant) colors.yellowPrimary.copy(alpha = 0.1f)
                            else colors.blackPrimary.copy(alpha = 0.1f)
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    footer()
                }
            }
        }
    }
}

@Composable
fun CyberpunkStatusCard(
    title: String,
    status: String,
    modifier: Modifier = Modifier,
    statusColor: Color = CyberpunkTheme.colors.greenPrimary,
    description: String? = null,
    icon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    darkVariant: Boolean = false,
    isActive: Boolean = true
) {
    val colors = CyberpunkTheme.colors

    CyberpunkCard(
        modifier = modifier,
        darkVariant = darkVariant,
        scanningEffect = isActive,
        headerContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let { iconContent ->
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = statusColor.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = statusColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp)
                    ) {
                        iconContent()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = title,
                    style = CyberpunkTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = statusColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = status,
                    style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = colors.white,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        footerContent = actions
    ) {
        description?.let { desc ->
            Text(
                text = desc,
                style = CyberpunkTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun CyberpunkMetricCard(
    title: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier,
    trend: CyberpunkTrend? = null,
    description: String? = null,
    color: Color = CyberpunkTheme.colors.borderGreen,
    darkVariant: Boolean = false
) {
    val colors = CyberpunkTheme.colors

    CyberpunkCard(
        modifier = modifier,
        darkVariant = darkVariant
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = CyberpunkTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = value,
                        style = CyberpunkTheme.typography.headlineMedium,
                        color = color,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = unit,
                        style = CyberpunkTheme.typography.bodyMedium,
                        color = color.copy(alpha = 0.7f),
                        modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                    )
                }

                description?.let { desc ->
                    Text(
                        text = desc,
                        style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        color = LocalContentColor.current.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            trend?.let { trendData ->
                val trendColor = when (trendData.direction) {
                    CyberpunkTrendDirection.Up -> colors.greenPrimary
                    CyberpunkTrendDirection.Down -> colors.redPrimary
                    CyberpunkTrendDirection.Stable -> colors.orangePrimary
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${if (trendData.direction == CyberpunkTrendDirection.Up) "+" else ""}${trendData.percentage}%",
                        style = CyberpunkTheme.typography.bodyMedium,
                        color = trendColor,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = trendData.period,
                        style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                        color = LocalContentColor.current.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
fun CyberpunkActionCard(
    title: String,
    description: String,
    primaryAction: String,
    onPrimaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryAction: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    darkVariant: Boolean = false,
    urgent: Boolean = false
) {
    val colors = CyberpunkTheme.colors

    CyberpunkCard(
        modifier = modifier,
        darkVariant = darkVariant,
        scanningEffect = urgent,
        headerContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let { iconContent ->
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                color = if (urgent) colors.redPrimary.copy(alpha = 0.2f)
                                else colors.borderGreen.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (urgent) colors.redPrimary else colors.borderGreen,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(6.dp)
                    ) {
                        iconContent()
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Text(
                    text = title,
                    style = CyberpunkTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            if (urgent) {
                Box(
                    modifier = Modifier
                        .background(
                            color = colors.redPrimary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "URGENT",
                        style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                        color = colors.white,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        footerContent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                secondaryAction?.let { action ->
                    CyberpunkButton(
                        onClick = onSecondaryClick ?: {},
                        text = action,
                        variant = CyberpunkButtonVariant.Blue,
                        modifier = Modifier.weight(1f)
                    )
                }

                CyberpunkButton(
                    onClick = onPrimaryClick,
                    text = primaryAction,
                    variant = if (urgent) CyberpunkButtonVariant.Red else CyberpunkButtonVariant.Green,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    ) {
        Text(
            text = description,
            style = CyberpunkTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )
    }
}

data class CyberpunkTrend(
    val direction: CyberpunkTrendDirection,
    val percentage: Float,
    val period: String
)

enum class CyberpunkTrendDirection {
    Up, Down, Stable
}

@Preview
@Composable
fun CyberpunkCardPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CyberpunkCard(
                modifier = Modifier.fillMaxWidth(),
                darkVariant = false,
                headerContent = {
                    Text(
                        text = "System Status",
                        style = CyberpunkTheme.typography.titleMedium
                    )
                    Text(
                        text = "Online",
                        style = CyberpunkTheme.typography.bodyMedium,
                        color = CyberpunkTheme.colors.greenPrimary
                    )
                }
            ) {
                Text(
                    text = "All systems operational. Neural link established and functioning within normal parameters.",
                    style = CyberpunkTheme.typography.bodyMedium
                )
            }

            CyberpunkStatusCard(
                title = "Neural Interface",
                status = "ACTIVE",
                description = "Connection established with 98% stability",
                darkVariant = true,
                statusColor = CyberpunkTheme.colors.greenPrimary,
                icon = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.greenPrimary)
                    )
                },
                actions = {
                    CyberpunkButton(
                        onClick = {},
                        text = "Disconnect",
                        variant = CyberpunkButtonVariant.Red
                    )
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CyberpunkMetricCard(
                    title = "CPU Usage",
                    value = "73",
                    unit = "%",
                    trend = CyberpunkTrend(
                        direction = CyberpunkTrendDirection.Up,
                        percentage = 12.3f,
                        period = "1h"
                    ),
                    color = CyberpunkTheme.colors.orangePrimary,
                    modifier = Modifier.weight(1f)
                )

                CyberpunkMetricCard(
                    title = "Memory",
                    value = "8.2",
                    unit = "GB",
                    trend = CyberpunkTrend(
                        direction = CyberpunkTrendDirection.Down,
                        percentage = -5.7f,
                        period = "1h"
                    ),
                    color = CyberpunkTheme.colors.bluePrimary,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun CyberpunkCardDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CyberpunkActionCard(
                title = "Security Alert",
                description = "Unauthorized access detected in sector 7. Immediate action required to secure the perimeter.",
                primaryAction = "Breach",
                onPrimaryClick = {},
                secondaryAction = "Ignore",
                onSecondaryClick = {},
                urgent = true,
                icon = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.redPrimary)
                    )
                }
            )

            CyberpunkStatusCard(
                title = "Hack Progress",
                status = "IN PROGRESS",
                description = "Breaking through ICE layer 3 of 5",
                statusColor = CyberpunkTheme.colors.orangePrimary,
                isActive = true,
                icon = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CyberpunkTheme.colors.orangePrimary)
                    )
                }
            )
        }
    }
}