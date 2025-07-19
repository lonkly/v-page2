package tech.vivienne.v_page2

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.*
import tech.vivienne.v_page2.design.atoms.*
import tech.vivienne.v_page2.design.molecules.*

@Preview
@Composable
fun TestMainPageRendering() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.blackPrimary)
                .padding(16.dp)
        ) {
            // Test the cards from MainPage
            Text("Testing Mobile Development Card:", color = Color.Red, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            
            // Mobile Development Card
            Box(modifier = Modifier.border(2.dp, Color.Red)) {
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
                        
                        // Test basic text first
                        Text(
                            "Basic Text: Should be visible",
                            color = CyberpunkTheme.colors.blackPrimary,
                            fontSize = 18.sp
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Then test list
                        CyberPunkList(
                            items = listOf(
                                "Test Item 1",
                                "Test Item 2"
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
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Check if content appears at all
            Text("If you see this red text, rendering is working", color = Color.Red, fontSize = 20.sp)
        }
    }
}