package tech.vivienne.v_page2.design.atoms

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Preview
@Composable
fun CyberpunkTextFieldDesktopPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var text1 by remember { mutableStateOf("") }
            CyberpunkTextField(
                value = text1,
                onValueChange = { text1 = it },
                placeholder = "Enter neural link ID",
                supportingText = "Required field"
            )

            var text2 by remember { mutableStateOf("") }
            CyberpunkTextField(
                value = text2,
                onValueChange = { text2 = it },
                placeholder = "Password",
                isPassword = true,
                supportingText = "Use encrypted passphrase"
            )

            var text3 by remember { mutableStateOf("Error state") }
            CyberpunkTextField(
                value = text3,
                onValueChange = { text3 = it },
                placeholder = "Invalid input",
                isError = true,
                supportingText = "Connection failed"
            )

            var text4 by remember { mutableStateOf("Disabled field") }
            CyberpunkTextField(
                value = text4,
                onValueChange = { text4 = it },
                placeholder = "Disabled",
                enabled = false,
                supportingText = "System locked"
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkTextFieldDarkDesktopPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var text1 by remember { mutableStateOf("") }
            CyberpunkTextField(
                value = text1,
                onValueChange = { text1 = it },
                placeholder = "Access Code",
                keyboardType = KeyboardType.Number
            )

            var text2 by remember { mutableStateOf("") }
            CyberpunkTextField(
                value = text2,
                onValueChange = { text2 = it },
                placeholder = "Secure Key",
                isPassword = true
            )
        }
    }
}