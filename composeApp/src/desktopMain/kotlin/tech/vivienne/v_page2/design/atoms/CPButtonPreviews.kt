package tech.vivienne.v_page2.design.atoms

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Preview
@Composable
fun CyberpunkButtonDesktopPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CyberpunkButton(
                onClick = {},
                text = "Connect",
                variant = CyberpunkButtonVariant.Red
            )

            CyberpunkButton(
                onClick = {},
                text = "Hack System",
                variant = CyberpunkButtonVariant.Green,
                glitchEffect = true
            )

            CyberpunkButton(
                onClick = {},
                text = "Decrypt",
                variant = CyberpunkButtonVariant.Blue,
                codeIndicator = "B-77"
            )

            CyberpunkButton(
                onClick = {},
                text = "Access",
                variant = CyberpunkButtonVariant.Purple,
                enabled = false
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkButtonDarkDesktopPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CyberpunkButton(
                onClick = {},
                text = "Neural Link",
                variant = CyberpunkButtonVariant.Red,
                glitchEffect = true
            )

            CyberpunkButton(
                onClick = {},
                text = "Breach",
                variant = CyberpunkButtonVariant.Green
            )
        }
    }
}