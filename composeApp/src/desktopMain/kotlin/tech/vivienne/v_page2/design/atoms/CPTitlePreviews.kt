package tech.vivienne.v_page2.design.atoms

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Preview
@Composable
fun CyberpunkTitleDesktopPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CyberpunkTitle(
                text = "System Status",
                level = CyberpunkTitleLevel.H1
            )

            CyberpunkTitle(
                text = "Neural Interface",
                level = CyberpunkTitleLevel.H2,
                glitchEffect = true
            )

            CyberpunkTitle(
                text = "Connection Active",
                level = CyberpunkTitleLevel.H3
            )

            CyberpunkTitle(
                text = "Data Stream",
                level = CyberpunkTitleLevel.H4,
                showCursor = false
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkTitleDarkDesktopPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CyberpunkTitle(
                text = "Cyberpunk 2077",
                level = CyberpunkTitleLevel.H1,
                glitchEffect = true
            )

            CyberpunkTitle(
                text = "Access Granted",
                level = CyberpunkTitleLevel.H2,
                color = CyberpunkTheme.colors.neonGreen
            )

            CyberpunkTitle(
                text = "Breach Protocol",
                level = CyberpunkTitleLevel.H3,
                color = CyberpunkTheme.colors.redPrimary
            )
        }
    }
}