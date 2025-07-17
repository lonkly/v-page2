package tech.vivienne.v_page2.design.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.*
import tech.vivienne.v_page2.design.atoms.*
import tech.vivienne.v_page2.design.molecules.*
import tech.vivienne.v_page2.design.organisms.*


@Preview
@Composable
fun PreviewCPButton() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkButton(
                text = "Primary Button",
                onClick = {},
            )
            CyberpunkButton(
                text = "Secondary Button",
                onClick = {},
            )
            CyberpunkButton(
                text = "Danger Button",
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPTextField() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkTextField(
                value = "Sample text",
                onValueChange = {},
                placeholder = "Username"
            )
            CyberpunkTextField(
                value = "",
                onValueChange = {},
                placeholder = "Password",
                isPassword = true
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPCheckbox() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkCheckbox(
                checked = true,
                onCheckedChange = {},
                label = "Checked"
            )
            CyberpunkCheckbox(
                checked = false,
                onCheckedChange = {},
                label = "Unchecked"
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPRadioButton() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkRadioButton(
                selected = true,
                onClick = {},
                label = "Selected"
            )
            CyberpunkRadioButton(
                selected = false,
                onClick = {},
                label = "Not Selected"
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPTitle() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkTitle(text = "Large Title")
            CyberpunkTitle(text = "Medium Title")
            CyberpunkTitle(text = "Small Title")
        }
    }
}

@Preview
@Composable
fun PreviewCPCard() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
        ) {
            CyberpunkCard(modifier = Modifier.width(300.dp)) {
                CyberpunkTitle(text = "Card Title")
                Spacer(modifier = Modifier.height(8.dp))
                CPParagraph(text = "This is a card with cyberpunk styling.")
            }
        }
    }
}

@Preview
@Composable
fun PreviewCPParagraph() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CPParagraph(
                text = "Normal paragraph with standard styling.",
                style = ParagraphStyle.NORMAL
            )
            CPParagraph(
                text = "Terminal style paragraph with monospace font.",
                style = ParagraphStyle.INVERSE
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPList() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CPList(
                items = listOf("Item 1", "Item 2", "Item 3"),
                listType = ListType.UNORDERED
            )
            CPList(
                items = listOf("First", "Second", "Third"),
                listType = ListType.ORDERED
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPLink() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
        ) {
            CPLink(
                text = "Click me",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPSeparator() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
                .width(300.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CPSeparator()
            CPSeparator(isGlitched = true)
        }
    }
}

@Preview
@Composable
fun PreviewCPFieldset() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
        ) {
            CPFieldset {
                CyberpunkTitle(text = "Fieldset Title")
                Spacer(modifier = Modifier.height(8.dp))
                CPParagraph(text = "Content inside fieldset")
            }
        }
    }
}

@Preview
@Composable
fun PreviewCPDropDown() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
        ) {
            CyberpunkDropdown(
                options = listOf("Option 1", "Option 2", "Option 3"),
                selectedOption = "Option 1",
                onOptionSelected = {},
                label = "Select Option"
            )
        }
    }
}

@Preview
@Composable
fun PreviewCPSection() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .size(400.dp, 200.dp)
        ) {
            CPSection(
                style = SectionStyle.YELLOW,
                border = SectionBorder.BOTH
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CyberpunkTitle(text = "Section Content")
                }
            }
        }
    }
}