package tech.vivienne.v_page2

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.*
import tech.vivienne.v_page2.design.atoms.*
import tech.vivienne.v_page2.design.molecules.*
import tech.vivienne.v_page2.design.organisms.*

@Composable
private fun IntroCard() {
    CyberpunkCard(
        modifier = Modifier.fillMaxWidth(),
        darkVariant = false // Yellow card with black text
    ) {
        Column(
            modifier = Modifier.padding(32.dp)
        ) {
            CyberpunkTitle(
                text = "KOTLIN MULTIPLATFORM SPECIALIST",
                level = CyberpunkTitleLevel.H2,
                color = CyberpunkTheme.colors.blackPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            CyberPunkParagraph(
                text = "I architect full-stack mobile solutions using Kotlin Multiplatform, achieving 95% code sharing across iOS, Android, and backend systems. With 15 years in mobile development - from Objective-C and Android 1.6 to modern SwiftUI and Compose - I've led teams of 20+ engineers through successful product launches from seed to Series C.",
                style = ParagraphStyle.NORMAL,
                isBlackSection = true
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
                        Spacer(modifier = Modifier.height(8.dp))
                        CyberPunkLink(
                            text = "Code Sharing",
                            onClick = {},
                            color = CyberpunkTheme.colors.blackPrimary,
                            textStyle = CyberpunkTheme.typography.bodySmall
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
                        Spacer(modifier = Modifier.height(8.dp))
                        CyberPunkLink(
                            text = "Build Speed",
                            onClick = {},
                            color = CyberpunkTheme.colors.blackPrimary,
                            textStyle = CyberpunkTheme.typography.bodySmall
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
                        Spacer(modifier = Modifier.height(8.dp))
                        CyberPunkLink(
                            text = "Team Size",
                            onClick = {},
                            color = CyberpunkTheme.colors.blackPrimary,
                            textStyle = CyberpunkTheme.typography.bodySmall
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
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
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
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
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
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
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
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
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
            modifier = Modifier.padding(32.dp)
        ) {
            CyberpunkTitle(
                text = "ENGINEERING PHILOSOPHY",
                level = CyberpunkTitleLevel.H3,
                color = CyberpunkTheme.colors.yellowPrimary,
                glitchEffect = false
            )
            CyberPunkSeparator(
                modifier = Modifier.padding(vertical = 16.dp),
                color = CyberpunkTheme.colors.yellowPrimary,
                isGlitched = true
            )
            CyberPunkParagraph(
                text = "I believe in pragmatic engineering - choosing technologies that ship products, not just impressive resumes. Whether it's designing scalable architectures, optimizing build systems, or mentoring teams, I focus on delivering measurable business impact.",
                style = ParagraphStyle.NORMAL,
                isBlackSection = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            CyberPunkList(
                items = listOf(
                    "Ship fast, iterate faster",
                    "Code is a liability, features are assets",
                    "Architecture should enable, not constrain",
                    "Teams over tools, always"
                ),
                modifier = Modifier.fillMaxWidth(),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = CyberpunkTheme.colors.yellowPrimary
                ),
                isGlitched = false,
                isBlackSection = true
            )
        }
    }
}

@Composable
fun MainPage() {
    CyberpunkTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.blackPrimary)
        ) {
            val isMobile = maxWidth < 768.dp
            var mobileMenuExpanded by remember { mutableStateOf(false) }
            
            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
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
                            modifier = Modifier.fillMaxWidth(0.6f).align(Alignment.CenterHorizontally),
                            isBlackSection = true
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        CyberpunkTitle(
                            text = "Full-Stack Mobile Architect • 15 Years Experience",
                            level = CyberpunkTitleLevel.H4,
                            color = CyberpunkTheme.colors.neonGreen,
                            glitchEffect = false,
                            showCursor = false
                        )
                    }
                }

                // Action Bar - Yellow background with black text
                CyberPunkSection(
                    modifier = Modifier.fillMaxWidth(),
                    style = SectionStyle.YELLOW,
                    border = SectionBorder.BOTTOM
                ) {
                    if (isMobile) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CyberpunkButton(
                                text = "DOWNLOAD CV",
                                onClick = { /* TODO: Implement CV download */ },
                                variant = CyberpunkButtonVariant.Red,
                                codeIndicator = "CV-01",
                                glitchEffect = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            CyberpunkButton(
                                text = "GITHUB",
                                onClick = { /* TODO: Open GitHub */ },
                                variant = CyberpunkButtonVariant.Green,
                                codeIndicator = "GH-02",
                                glitchEffect = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            CyberpunkButton(
                                text = "LINKEDIN",
                                onClick = { /* TODO: Open LinkedIn */ },
                                variant = CyberpunkButtonVariant.Blue,
                                codeIndicator = "LI-03",
                                modifier = Modifier.fillMaxWidth()
                            )
                            CyberpunkButton(
                                text = "EMAIL",
                                onClick = { /* TODO: Open email */ },
                                variant = CyberpunkButtonVariant.Purple,
                                codeIndicator = "EM-04",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CyberpunkButton(
                                text = "DOWNLOAD CV",
                                onClick = { /* TODO: Implement CV download */ },
                                variant = CyberpunkButtonVariant.Red,
                                codeIndicator = "CV-01",
                                glitchEffect = true,
                                modifier = Modifier.weight(1f).padding(horizontal = 8.dp).offset(y = -30.dp)
                            )
                            CyberpunkButton(
                                text = "GITHUB",
                                onClick = { /* TODO: Open GitHub */ },
                                variant = CyberpunkButtonVariant.Green,
                                codeIndicator = "GH-02",
                                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
                            )
                            CyberpunkButton(
                                text = "LINKEDIN",
                                onClick = { /* TODO: Open LinkedIn */ },
                                variant = CyberpunkButtonVariant.Blue,
                                codeIndicator = "LI-03",
                                modifier = Modifier.weight(1f).padding(horizontal = 8.dp).offset(y = -20.dp)
                            )
                            CyberpunkButton(
                                text = "EMAIL",
                                onClick = { /* TODO: Open email */ },
                                variant = CyberpunkButtonVariant.Purple,
                                codeIndicator = "EM-04",
                                modifier = Modifier.weight(1f).padding(horizontal = 8.dp).offset(y = -10.dp)
                            )
                        }
                    }
                }

                // Main Content - Black background section
                CyberPunkSection(
                    modifier = Modifier.fillMaxWidth(),
                    style = SectionStyle.BLACK,
                    border = SectionBorder.BOTH
                ) {
                    if (isMobile) {
                        // Mobile layout - no aside menu in main content
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp, horizontal = 16.dp)
                        ) {
                            // Intro Card - use extracted component
                            IntroCard()

                            Spacer(modifier = Modifier.height(32.dp))

                            // Technical Skills Section
                            CyberpunkTitle(
                                text = "TECHNICAL SKILLS",
                                level = CyberpunkTitleLevel.H2,
                                color = CyberpunkTheme.colors.yellowPrimary
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            // Skills Cards - use extracted component
                            SkillsCards()

                            Spacer(modifier = Modifier.height(32.dp))

                            // Experience Timeline
                            CyberpunkTitle(
                                text = "EXPERIENCE TIMELINE",
                                level = CyberpunkTitleLevel.H3,
                                color = CyberpunkTheme.colors.yellowPrimary
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
                                modifier = Modifier.fillMaxWidth(),
                                isBlackSection = true
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            // Philosophy Card - use extracted component
                            PhilosophyCard()
                        }
                    } else {
                        // Desktop layout - with aside menu
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 40.dp, horizontal = 32.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            // Left Column - Main Content
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .widthIn(max = 800.dp)
                            ) {
                                // Intro Card - use extracted component
                                IntroCard()

                                Spacer(modifier = Modifier.height(32.dp))

                                // Technical Skills Section
                                CyberpunkTitle(
                                    text = "TECHNICAL SKILLS",
                                    level = CyberpunkTitleLevel.H2,
                                    color = CyberpunkTheme.colors.yellowPrimary,
                                    showUnderline = true,
                                    useGlitchingUnderline = true
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                
                                // Skills Cards - use extracted component
                                SkillsCards()

                                Spacer(modifier = Modifier.height(32.dp))

                                // Experience Timeline
                                CyberpunkTitle(
                                    text = "EXPERIENCE TIMELINE",
                                    level = CyberpunkTitleLevel.H3,
                                    color = CyberpunkTheme.colors.yellowPrimary
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
                                    modifier = Modifier.fillMaxWidth(),
                                    isBlackSection = true
                                )

                                Spacer(modifier = Modifier.height(32.dp))

                                // Philosophy Card - use extracted component
                                PhilosophyCard()
                                Spacer(modifier = Modifier.height(32.dp))
                            }

                            // Spacer between columns
                            Spacer(modifier = Modifier.width(40.dp))

                            // Right Column - Aside Menu
                            Box(
                                modifier = Modifier
                                    .width(240.dp)
                                    .fillMaxHeight()
                            ) {
                                CyberPunkAside(
                                    items = listOf(
                                        AsideMenuItem("About", {}),
                                        AsideMenuItem("Experience", {}),
                                        AsideMenuItem("Projects", {}),
                                        AsideMenuItem("Skills", {}),
                                        AsideMenuItem("Contact", {})
                                    ),
                                    modifier = Modifier.offset(y = 90.dp)
                                )
                            }
                        }
                    }
                }

                // Footer Section - Yellow background
                CyberPunkSection(
                    modifier = Modifier.fillMaxWidth(),
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
                            text = "Open to remote Principal/Staff engineering roles where I can drive technical strategy and architect systems at scale.",
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
            
            // Mobile Menu - Bottom Sheet Style
            if (isMobile) {
                val menuOffsetY by animateDpAsState(
                    targetValue = if (mobileMenuExpanded) 0.dp else 250.dp,
                    animationSpec = tween(300)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .offset(y = menuOffsetY)
                        .background(CyberpunkTheme.colors.blackPrimary)
                        .border(
                            width = 2.dp,
                            color = CyberpunkTheme.colors.borderGreen,
                            shape = RectangleShape
                        )
                        .clickable { mobileMenuExpanded = !mobileMenuExpanded }
                ) {
                    Column {
                        // Handle bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(CyberpunkTheme.colors.yellowPrimary)
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(4.dp)
                                    .background(CyberpunkTheme.colors.blackPrimary)
                            )
                        }
                        
                        // Menu items
                        if (mobileMenuExpanded) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                CyberpunkButton(
                                    text = "About",
                                    onClick = { },
                                    variant = CyberpunkButtonVariant.Green,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                CyberpunkButton(
                                    text = "Experience",
                                    onClick = { },
                                    variant = CyberpunkButtonVariant.Green,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                CyberpunkButton(
                                    text = "Projects",
                                    onClick = { },
                                    variant = CyberpunkButtonVariant.Green,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                CyberpunkButton(
                                    text = "Skills",
                                    onClick = { },
                                    variant = CyberpunkButtonVariant.Green,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                CyberpunkButton(
                                    text = "Contact",
                                    onClick = { },
                                    variant = CyberpunkButtonVariant.Green,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}