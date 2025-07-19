package tech.vivienne.v_page2.design.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.atoms.CyberpunkButton
import tech.vivienne.v_page2.design.atoms.CyberpunkButtonVariant
import tech.vivienne.v_page2.design.atoms.CyberpunkTitle
import tech.vivienne.v_page2.design.atoms.CyberpunkTitleLevel
import tech.vivienne.v_page2.design.molecules.CyberpunkCard
import tech.vivienne.v_page2.design.scanningEffect

@Composable
fun CyberpunkScreen(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = false,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundPattern: Boolean = true,
    scrollable: Boolean = true,
    scanningEffect: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    val colors = tech.vivienne.v_page2.design.CyberpunkTheme.colors
    val backgroundColor = if (darkVariant) colors.blackPrimary else colors.yellowPrimary

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .let { mod ->
                if (backgroundPattern) {
                    mod.drawBehind {
                        drawCyberpunkBackground(colors, darkVariant)
                    }
                } else {
                    mod
                }
            }
            .scanningEffect(
                isActive = scanningEffect,
                direction = tech.vivienne.v_page2.design.ScanDirection.Vertical,
                color = colors.borderGreen
            ),
        topBar = topBar ?: {},
        bottomBar = bottomBar ?: {},
        floatingActionButton = floatingActionButton ?: {},
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = backgroundColor,
        contentColor = if (darkVariant) colors.yellowPrimary else colors.blackPrimary
    ) { paddingValues ->
        if (scrollable) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                content(paddingValues)
            }
        } else {
            content(paddingValues)
        }
    }
}

@Composable
fun CyberpunkSection(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = false,
    title: String? = null,
    subtitle: String? = null,
    headerActions: @Composable RowScope.() -> Unit = {},
    bordered: Boolean = true,
    scanningEffect: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = tech.vivienne.v_page2.design.CyberpunkTheme.colors
    val backgroundColor = if (darkVariant) colors.blackPrimary else colors.yellowPrimary
    val contentColor = if (darkVariant) colors.yellowPrimary else colors.blackPrimary

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .let { mod ->
                if (bordered) {
                    mod.border(
                        width = 2.dp,
                        color = colors.borderGreen,
                        shape = RectangleShape
                    )
                } else {
                    mod
                }
            }
            .scanningEffect(
                isActive = scanningEffect,
                direction = tech.vivienne.v_page2.design.ScanDirection.Horizontal,
                color = colors.borderGreen
            )
            .padding(16.dp)
    ) {
        // Header
        if (title != null || subtitle != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    title?.let { titleText ->
                        CyberpunkTitle(
                            text = titleText,
                            level = CyberpunkTitleLevel.H2,
                            color = contentColor,
                            showCursor = false
                        )
                    }

                    subtitle?.let { subtitleText ->
                        Text(
                            text = subtitleText,
                            style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium,
                            color = contentColor.copy(alpha = 0.7f)
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    headerActions()
                }
            }
        }

        // Content
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

@Composable
fun CyberpunkGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    verticalSpacing: androidx.compose.ui.unit.Dp = 16.dp,
    horizontalSpacing: androidx.compose.ui.unit.Dp = 16.dp,
    content: @Composable () -> Unit
) {
    // Simple grid implementation using Column and Row
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)
    ) {
        content()
    }
}

@Composable
fun CyberpunkDashboard(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = false,
    title: String,
    subtitle: String? = null,
    headerActions: @Composable RowScope.() -> Unit = {},
    widgets: @Composable ColumnScope.() -> Unit
) {
    val colors = tech.vivienne.v_page2.design.CyberpunkTheme.colors

    CyberpunkSection(
        modifier = modifier,
        darkVariant = darkVariant,
        title = title,
        subtitle = subtitle,
        headerActions = headerActions,
        bordered = false,
        scanningEffect = true
    ) {
        widgets()
    }
}

@Composable
fun CyberpunkSidebar(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = true,
    width: androidx.compose.ui.unit.Dp = 280.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = tech.vivienne.v_page2.design.CyberpunkTheme.colors
    val backgroundColor = if (darkVariant) colors.blackPrimary else colors.yellowPrimary
    val contentColor = if (darkVariant) colors.yellowPrimary else colors.blackPrimary

    Column(
        modifier = modifier
            .width(width)
            .fillMaxHeight()
            .background(backgroundColor)
            .border(
                width = 2.dp,
                color = colors.borderGreen,
                shape = RectangleShape
            )
            .padding(16.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            content()
        }
    }
}

@Composable
fun CyberpunkTwoPane(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = false,
    sidebarWidth: androidx.compose.ui.unit.Dp = 280.dp,
    sidebar: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        CyberpunkSidebar(
            width = sidebarWidth,
            darkVariant = !darkVariant, // Invert for contrast
            content = sidebar
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            CyberpunkSection(
                modifier = Modifier.weight(1f),
                darkVariant = darkVariant,
                bordered = false,
                content = content
            )
        }
    }
}

@Composable
fun CyberpunkStatusBar(
    modifier: Modifier = Modifier,
    darkVariant: Boolean = true,
    items: List<CyberpunkStatusItem>
) {
    val colors = tech.vivienne.v_page2.design.CyberpunkTheme.colors
    val backgroundColor = if (darkVariant) colors.blackPrimary else colors.yellowPrimary
    val contentColor = if (darkVariant) colors.yellowPrimary else colors.blackPrimary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = colors.borderGreen,
                shape = RectangleShape
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = item.color,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )

                Text(
                    text = item.label,
                    style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = contentColor
                )

                item.value?.let { value ->
                    Text(
                        text = value,
                        style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        color = item.color,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawCyberpunkBackground(
    colors: tech.vivienne.v_page2.design.CyberpunkColorScheme,
    darkVariant: Boolean
) {
    val dotSize = 1.dp.toPx()
    val spacing = 20.dp.toPx()
    val dotColor = if (darkVariant)
        colors.yellowPrimary.copy(alpha = 0.1f)
    else
        colors.blackPrimary.copy(alpha = 0.1f)

    // Draw dot grid pattern
    for (x in 0 until (size.width / spacing).toInt()) {
        for (y in 0 until (size.height / spacing).toInt()) {
            drawCircle(
                color = dotColor,
                radius = dotSize / 2,
                center = Offset(x * spacing, y * spacing)
            )
        }
    }

    // Draw scanlines
    val lineSpacing = 4.dp.toPx()
    val lineColor = if (darkVariant)
        colors.borderGreen.copy(alpha = 0.05f)
    else
        colors.borderGreen.copy(alpha = 0.03f)

    for (y in 0 until (size.height / lineSpacing).toInt()) {
        drawLine(
            color = lineColor,
            start = Offset(0f, y * lineSpacing),
            end = Offset(size.width, y * lineSpacing),
            strokeWidth = 1.dp.toPx()
        )
    }
}

data class CyberpunkStatusItem(
    val label: String,
    val value: String? = null,
    val color: Color
)

@Preview
@Composable
fun CyberpunkLayoutsPreview() {
    _root_ide_package_.tech.vivienne.v_page2.design.CyberpunkTheme {
        CyberpunkScreen(
            topBar = {
                CyberpunkStatusBar(
                    items = listOf(
                        CyberpunkStatusItem(
                            "CPU",
                            "73%",
                            tech.vivienne.v_page2.design.CyberpunkTheme.colors.orangePrimary
                        ),
                        CyberpunkStatusItem(
                            "MEM",
                            "8.2GB",
                            tech.vivienne.v_page2.design.CyberpunkTheme.colors.bluePrimary
                        ),
                        CyberpunkStatusItem(
                            "NET",
                            "Online",
                            tech.vivienne.v_page2.design.CyberpunkTheme.colors.greenPrimary
                        )
                    )
                )
            },
            backgroundPattern = true,
            scanningEffect = true
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CyberpunkSection(
                    title = "System Status",
                    subtitle = "Neural interface monitoring",
                    headerActions = {
                        CyberpunkButton(
                            onClick = {},
                            text = "Refresh",
                            variant = CyberpunkButtonVariant.Blue
                        )
                    }
                ) {
                    Text(
                        text = "All systems operational. Neural link established and functioning within normal parameters.",
                        style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium
                    )
                }

                CyberpunkSection(
                    title = "Active Connections",
                    darkVariant = true,
                    scanningEffect = true
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(3) { index ->
                            CyberpunkCard(
                                modifier = Modifier.fillMaxWidth(),
                                darkVariant = true
                            ) {
                                Text(
                                    text = "Connection ${index + 1}",
                                    style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CyberpunkTwoPanePreview() {
    _root_ide_package_.tech.vivienne.v_page2.design.CyberpunkTheme {
        CyberpunkTwoPane(
            darkVariant = false,
            sidebar = {
                CyberpunkTitle(
                    text = "Navigation",
                    level = CyberpunkTitleLevel.H3,
                    showCursor = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(5) { index ->
                        Text(
                            text = "Menu Item ${index + 1}",
                            style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            },
            content = {
                CyberpunkDashboard(
                    title = "Dashboard",
                    subtitle = "System overview",
                    headerActions = {
                        CyberpunkButton(
                            onClick = {},
                            text = "Settings",
                            variant = CyberpunkButtonVariant.Green
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        repeat(3) { index ->
                            CyberpunkCard(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Widget ${index + 1}",
                                    style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun CyberpunkLayoutsDarkPreview() {
    _root_ide_package_.tech.vivienne.v_page2.design.CyberpunkTheme(darkTheme = true) {
        CyberpunkScreen(
            darkVariant = true,
            topBar = {
                CyberpunkStatusBar(
                    darkVariant = true,
                    items = listOf(
                        CyberpunkStatusItem(
                            "BREACH",
                            "Active",
                            tech.vivienne.v_page2.design.CyberpunkTheme.colors.redPrimary
                        ),
                        CyberpunkStatusItem(
                            "ICE",
                            "Detected",
                            tech.vivienne.v_page2.design.CyberpunkTheme.colors.orangePrimary
                        ),
                        CyberpunkStatusItem(
                            "STEALTH",
                            "75%",
                            tech.vivienne.v_page2.design.CyberpunkTheme.colors.bluePrimary
                        )
                    )
                )
            },
            backgroundPattern = true
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CyberpunkSection(
                    title = "Hack Progress",
                    subtitle = "Breaching target system",
                    darkVariant = true,
                    scanningEffect = true,
                    headerActions = {
                        CyberpunkButton(
                            onClick = {},
                            text = "Abort",
                            variant = CyberpunkButtonVariant.Red
                        )
                    }
                ) {
                    Text(
                        text = "Breaking through ICE layer 3 of 5. Estimated time remaining: 2 minutes 34 seconds.",
                        style = tech.vivienne.v_page2.design.CyberpunkTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}