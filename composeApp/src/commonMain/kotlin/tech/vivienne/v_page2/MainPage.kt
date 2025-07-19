package tech.vivienne.v_page2

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import tech.vivienne.v_page2.design.*
import tech.vivienne.v_page2.design.atoms.*
import tech.vivienne.v_page2.design.molecules.*
import tech.vivienne.v_page2.design.organisms.*
import tech.vivienne.v_page2.platform.isTouchDevice

@Composable
private fun IntroCard() {
    CyberpunkCard(
        modifier = Modifier.fillMaxWidth(),
        darkVariant = true // Yellow card with black text
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            CyberpunkTitle(
                text = "KOTLIN MULTIPLATFORM SPECIALIST",
                level = CyberpunkTitleLevel.H2,
                color = CyberpunkTheme.colors.yellowPrimary,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CyberPunkParagraph(
                text = "I architect full-stack mobile solutions using Kotlin Multiplatform, achieving 95% code sharing across iOS, Android, and backend systems. With 15 years in mobile development - from Objective-C and Android 1.6 to modern SwiftUI and Compose - I've led teams of 20+ engineers through successful product launches from seed to Series C.",
                style = ParagraphStyle.NORMAL,
                isBlackSection = false
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            // Key Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CyberPunkFieldset(
                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    ) {
                        CyberpunkTitle(
                            text = "95%",
                            level = CyberpunkTitleLevel.H3,
                            color = CyberpunkTheme.colors.redPrimary,
                            showCursor = false
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CyberPunkLink(
                            text = "Code Sharing",
                            onClick = {},
                            color = CyberpunkTheme.colors.yellowPrimary,
                            textStyle = CyberpunkTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
                CyberPunkFieldset(
                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    ) {
                        CyberpunkTitle(
                            text = "6x",
                            level = CyberpunkTitleLevel.H3,
                            color = CyberpunkTheme.colors.greenPrimary,
                            showCursor = false
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CyberPunkLink(
                            text = "Build Speed",
                            onClick = {},
                            color = CyberpunkTheme.colors.yellowPrimary,
                            textStyle = CyberpunkTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
                CyberPunkFieldset(
                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    ) {
                        CyberpunkTitle(
                            text = "20+",
                            level = CyberpunkTitleLevel.H3,
                            color = CyberpunkTheme.colors.bluePrimary,
                            showCursor = false
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CyberPunkLink(
                            text = "Team Size",
                            onClick = {},
                            color = CyberpunkTheme.colors.yellowPrimary,
                            textStyle = CyberpunkTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillsCards() {
    Column {
        // Mobile Development
        CyberpunkCard(
            modifier = Modifier.fillMaxWidth(),
            darkVariant = false
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                CyberpunkTitle(
                    text = "MOBILE DEVELOPMENT",
                    level = CyberpunkTitleLevel.H3,
                    color = CyberpunkTheme.colors.redPrimary,
                    showCursor = false
                )
                Spacer(modifier = Modifier.height(16.dp))
                CyberPunkList(
                    items = listOf(
                        "Architected Kotlin Multiplatform solutions sharing code across iOS, Android, and backend",
                        "Pioneered SwiftUI 1.0 adoption with custom state management",
                        "Improved app stability to 99.5% crash-free rate",
                        "Implemented reactive patterns using RxSwift and Coroutines",
                        "Built enterprise applications for Fortune 500 clients"
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                        color = CyberpunkTheme.colors.blackPrimary
                    ),
                    isGlitched = false
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Backend Development
        CyberpunkCard(
            modifier = Modifier.fillMaxWidth(),
            darkVariant = true
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                CyberpunkTitle(
                    text = "BACKEND DEVELOPMENT",
                    level = CyberpunkTitleLevel.H3,
                    color = CyberpunkTheme.colors.greenPrimary,
                    showCursor = false
                )
                Spacer(modifier = Modifier.height(16.dp))
                CyberPunkList(
                    items = listOf(
                        "Designed Ktor microservices with shared KMP models",
                        "Built blockchain data marketplace on custom EOSIO network",
                        "Implemented dynamic server-driven UI parsing XML to SwiftUI",
                        "Created generic response parsing system using Swift generics"
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                        color = CyberpunkTheme.colors.yellowPrimary
                    ),
                    isGlitched = false,
                    isBlackSection = true
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // DevOps
        CyberpunkCard(
            modifier = Modifier.fillMaxWidth(),
            darkVariant = false
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                CyberpunkTitle(
                    text = "DEVOPS & BUILD ENGINEERING",
                    level = CyberpunkTitleLevel.H3,
                    color = CyberpunkTheme.colors.bluePrimary,
                    showCursor = false
                )
                Spacer(modifier = Modifier.height(16.dp))
                CyberPunkList(
                    items = listOf(
                        "Unified 30 retailer CI/CD pipelines into single system",
                        "Reduced build times from 90 to 15 minutes",
                        "Implemented Gradle remote caching for 80-developer team",
                        "Migrated infrastructure saving $20K annually"
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                        color = CyberpunkTheme.colors.blackPrimary
                    )
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Leadership
        CyberpunkCard(
            modifier = Modifier.fillMaxWidth(),
            darkVariant = true
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                CyberpunkTitle(
                    text = "LEADERSHIP & ARCHITECTURE",
                    level = CyberpunkTitleLevel.H3,
                    color = CyberpunkTheme.colors.purplePrimary,
                    showCursor = false
                )
                Spacer(modifier = Modifier.height(16.dp))
                CyberPunkList(
                    items = listOf(
                        "Led engineering teams up to 20 developers",
                        "Designed scalable architectures using clean principles",
                        "Established documentation and pairing practices",
                        "Drove technical decisions balancing business constraints"
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                        color = CyberpunkTheme.colors.yellowPrimary
                    ),
                    isGlitched = false,
                    isBlackSection = true
                )
            }
        }
    }
}

@Composable
private fun PhilosophyCard() {
    CyberpunkCard(
        modifier = Modifier.fillMaxWidth(),
        darkVariant = true,
        scanningEffect = false
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            CyberpunkTitle(
                text = "ENGINEERING PHILOSOPHY",
                level = CyberpunkTitleLevel.H3,
                color = CyberpunkTheme.colors.yellowPrimary,
                glitchEffect = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            CyberPunkParagraph(
                text = "I believe in pragmatic engineering - choosing technologies that ship products, not just impressive resumes. Whether it's designing scalable architectures, optimizing build systems, or mentoring teams, I focus on delivering measurable business impact.",
                style = ParagraphStyle.NORMAL,
                isBlackSection = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            CyberPunkSeparator(
                modifier = Modifier.padding(vertical = 16.dp),
                color = CyberpunkTheme.colors.yellowPrimary,
                isGlitched = true
            )
            CyberPunkList(
                items = listOf(
                    "Ship fast, iterate faster",
                    "Code is a liability, features are assets",
                    "Architecture should enable, not constrain",
                    "Teams over tools, always"
                ),
                modifier = Modifier.fillMaxWidth(),
                textStyle = CyberpunkTheme.typography.bodyMedium.copy(
                    color = CyberpunkTheme.colors.yellowPrimary
                ),
                isGlitched = false,
                isBlackSection = true
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun MainPage() {
    CyberpunkTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.blackPrimary)
        ) {
            val windowSizeClass = calculateWindowSizeClass(maxWidth, maxHeight)
            val isCompactScreen = windowSizeClass.isCompact
            val isTouch = isTouchDevice()
            // Consider it "mobile" for interaction if it's either a small screen OR a touch device
            // This covers phones, tablets, iPads, and touch-enabled laptops
            val isMobile = isCompactScreen || isTouch
            val scrollState = rememberScrollState()
            val coroutineScope = rememberCoroutineScope()
            
            // Track section layout info
            val sectionPositions = remember { mutableMapOf<String, Float>() }
            val density = LocalDensity.current
            
            // Create a modifier to track scroll positions
            fun Modifier.trackScrollPosition(sectionId: String) = this
                .onGloballyPositioned { coordinates ->
                    sectionPositions[sectionId] = coordinates.positionInParent().y
                }
            
            // Scroll to section function with bounds checking and offset
            fun scrollToSection(sectionId: String) {
                coroutineScope.launch {
                    println("Scrolling to section: $sectionId, positions: $sectionPositions")
                    sectionPositions[sectionId]?.let { position ->
                        // Add a small offset for better visual alignment
                        val offset = with(density) { 80.dp.toPx() }
                        val targetScroll = (position - offset).toInt().coerceIn(0, scrollState.maxValue)
                        println("Target scroll position: $targetScroll")
                        scrollState.animateScrollTo(targetScroll)
                    } ?: println("Section $sectionId not found in positions map")
                }
            }
            
            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Hero Section - Black background with yellow text
                CyberPunkSection(
                    modifier = Modifier.fillMaxWidth(),
                    style = SectionStyle.BLACK,
                    border = SectionBorder.BOTTOM
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 60.dp, horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CyberpunkTitle(
                            text = "VIVIENNE FOSH",
                            level = CyberpunkTitleLevel.H1,
                            color = CyberpunkTheme.colors.yellowPrimary,
                            glitchEffect = false,
                            useGlitchingUnderline = true
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CyberPunkParagraph(
                            text = "PRINCIPAL SOFTWARE ENGINEER",
                            style = ParagraphStyle.INVERSE,
                            modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterHorizontally),
                            isBlackSection = true,
                            textStyle = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        CyberpunkTitle(
                            text = "Full-Stack Mobile Architect • 15 Years Experience",
                            level = CyberpunkTitleLevel.H4,
                            color = CyberpunkTheme.colors.neonGreen,
                            glitchEffect = false,
                            showCursor = true,
                            showUnderline = false
                        )
                    }
                }

                // Action Bar - Yellow background with black text
                CyberPunkSection(
                    modifier = Modifier.fillMaxWidth(),
                    style = SectionStyle.YELLOW,
                    border = SectionBorder.BOTTOM
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        maxItemsInEachRow = when (windowSizeClass.widthSizeClass) {
                            WindowSizeClass.Compact -> 1
                            WindowSizeClass.Medium -> 2
                            WindowSizeClass.Expanded -> 4
                        }
                    ) {
                        val buttonModifier = when (windowSizeClass.widthSizeClass) {
                            WindowSizeClass.Compact -> Modifier.fillMaxWidth(0.8f)
                            WindowSizeClass.Medium -> Modifier.weight(1f).padding(horizontal = 8.dp)
                            WindowSizeClass.Expanded -> Modifier.weight(1f).padding(horizontal = 16.dp)
                        }
                        
                        CyberpunkButton(
                            text = "DOWNLOAD CV",
                            onClick = { /* TODO: Implement CV download */ },
                            variant = CyberpunkButtonVariant.Red,
                            codeIndicator = "CV-01",
                            glitchEffect = true,
                            modifier = buttonModifier.then(
                                if (!isMobile) Modifier.offset(y = (-30).dp) else Modifier
                            )
                        )
                        CyberpunkButton(
                            text = "GITHUB",
                            onClick = { openUrl("https://www.github.com/lonkly/") },
                            variant = CyberpunkButtonVariant.Green,
                            codeIndicator = "GH-02",
                            glitchEffect = false,
                            modifier = buttonModifier
                        )
                        CyberpunkButton(
                            text = "LINKEDIN",
                            onClick = { openUrl("https://www.linkedin.com/in/lonkly/") },
                            variant = CyberpunkButtonVariant.Blue,
                            codeIndicator = "LI-03",
                            modifier = buttonModifier.then(
                                if (!isMobile) Modifier.offset(y = (-20).dp) else Modifier
                            )
                        )
                        CyberpunkButton(
                            text = "EMAIL",
                            onClick = { openUrl("mailto:vifosh@gmail.com") },
                            variant = CyberpunkButtonVariant.Purple,
                            codeIndicator = "EM-04",
                            modifier = buttonModifier.then(
                                if (!isMobile) Modifier.offset(y = (-10).dp) else Modifier
                            )
                        )
                    }
                }

                // Main Content - Black background section
                CyberPunkSection(
                    modifier = Modifier.fillMaxWidth(),
                    style = SectionStyle.BLACK,
                    border = SectionBorder.BOTH
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = if (isMobile) 20.dp else 40.dp,
                                horizontal = if (isMobile) 16.dp else 32.dp
                            ),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Main Content Column
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .widthIn(max = when (windowSizeClass.widthSizeClass) {
                                    WindowSizeClass.Compact -> Dp.Infinity
                                    WindowSizeClass.Medium -> 720.dp
                                    WindowSizeClass.Expanded -> 800.dp
                                })
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            // Intro Card - use extracted component
                            Box(
                                modifier = Modifier.trackScrollPosition("about")
                            ) {
                                IntroCard()
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Technical Skills Section
                            CyberpunkTitle(
                                text = "TECHNICAL SKILLS",
                                level = CyberpunkTitleLevel.H2,
                                color = CyberpunkTheme.colors.yellowPrimary,
                                modifier = Modifier.trackScrollPosition("skills"),
                                useGlitchingUnderline = true,
                                showUnderline = true
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            // Skills Cards - use extracted component
                            SkillsCards()

                            Spacer(modifier = Modifier.height(16.dp))

                            // Experience Timeline
                            CyberpunkTitle(
                                text = "EXPERIENCE TIMELINE",
                                level = CyberpunkTitleLevel.H3,
                                color = CyberpunkTheme.colors.yellowPrimary,
                                modifier = Modifier.trackScrollPosition("experience")
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            CyberPunkSteps(
                                steps = listOf(
                                    StepItem("Principal Engineer\nCurrent", true),
                                    StepItem("Staff Engineer\n2020-2023", false),
                                    StepItem("Senior Engineer\n2017-2020", false),
                                    StepItem("Mobile Lead\n2014-2017", false),
                                    StepItem("iOS Developer\n2009-2014", false)
                                ),
                                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                                isBlackSection = true
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            // Philosophy Card - use extracted component
                            Box(
                                modifier = Modifier.trackScrollPosition("philosophy")
                            ) {
                                PhilosophyCard()
                            }

                            Spacer(modifier = Modifier.height(32.dp))
                        }
                        
                        // Add spacers on desktop for layout balance
                        if (!isMobile) {
                            Spacer(modifier = Modifier.width(40.dp))
                            Spacer(modifier = Modifier.width(240.dp))
                        }
                    }
                }

                // Footer Section - Yellow background
                CyberPunkSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .trackScrollPosition("contact"),
                    style = SectionStyle.YELLOW,
                    border = SectionBorder.BOTH
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CyberpunkTitle(
                            text = "LOCATION: CHARLOTTESVILLE, VA",
                            level = CyberpunkTitleLevel.H4,
                            color = CyberpunkTheme.colors.blackPrimary,
                            showCursor = false
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CyberPunkParagraph(
                            text = "Open to remote principal/staff engineering roles where I can drive technical strategy and architect systems at scale.",
                            style = ParagraphStyle.NORMAL,
                            isBlackSection = true,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            var emailOptIn by remember { mutableStateOf(false) }
                            CyberpunkCheckbox(
                                checked = emailOptIn,
                                onCheckedChange = { emailOptIn = it },
                                label = "Notify me about opportunities",
                                labelColor = CyberpunkTheme.colors.blackPrimary
                            )
                        }
                    }
                }
            }
            
            // Track expanded state for mobile menu
            var mobileMenuExpanded by remember { mutableStateOf(false) }
            
            // Background click handler for mobile - MUST be before menus in z-order
            if (isMobile && mobileMenuExpanded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            mobileMenuExpanded = false
                        }
                )
            }
            
            // Main navigation menu - positioned at bottom right
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp)
                    .wrapContentHeight()
            ) {
                CyberPunkAside(
                    items = listOf(
                        AsideMenuItem("About", { scrollToSection("about") }),
                        AsideMenuItem("Experience", { scrollToSection("experience") }),
                        AsideMenuItem("Skills", { scrollToSection("skills") }),
                        AsideMenuItem("Philosophy", { scrollToSection("philosophy") }),
                        AsideMenuItem("Contact", { scrollToSection("contact") })
                    ),
                    modifier = Modifier.wrapContentHeight(),
                    width = if (isMobile) 200.dp else 240.dp,
                    collapsedOffset = if (isMobile) 80.dp else 43.dp,
                    itemHeight = 40.dp,
                    isMobile = isMobile,
                    onExpandedChange = { expanded -> 
                        if (isMobile) mobileMenuExpanded = expanded
                    }
                )
            }
            
            // Top WASM Menu - positioned at top right
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 20.dp)
            ) {
                CyberPunkAside(
                    items = listOf(
                        AsideMenuItem("Coded with WASM", { openUrl("https://kotlinlang.org/docs/wasm-overview.html") })
                    ),
                    modifier = Modifier,
                    width = if (isMobile) 250.dp else 300.dp,
                    collapsedOffset = if (isMobile) 100.dp else 60.dp,
                    itemHeight = 40.dp,
                    isMobile = isMobile
                )
            }
        }
    }
}