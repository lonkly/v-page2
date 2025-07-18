package tech.vivienne.v_page2.design.preview

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.*
import tech.vivienne.v_page2.design.atoms.*
import tech.vivienne.v_page2.design.molecules.*
import tech.vivienne.v_page2.design.organisms.*

@Preview
@Composable
fun CPButtonPreview() {
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
                variant = CyberpunkButtonVariant.Red
            )
            CyberpunkButton(
                text = "Secondary Button",
                onClick = {},
                variant = CyberpunkButtonVariant.Green
            )
            CyberpunkButton(
                text = "Danger Button",
                onClick = {},
                variant = CyberpunkButtonVariant.Purple
            )
        }
    }
}

@Preview
@Composable
fun CPTextFieldPreview() {
    CyberpunkTheme {
        var text by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = "Username"
            )
            CyberpunkTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = "Password",
                isPassword = true
            )
        }
    }
}

@Preview
@Composable
fun CPCheckboxPreview() {
    CyberpunkTheme {
        var checked by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
        ) {
            CyberpunkCheckbox(
                checked = checked,
                onCheckedChange = { checked = it },
                label = "Accept terms and conditions"
            )
        }
    }
}

@Preview
@Composable
fun CPTitlePreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkTitle(
                text = "Large Title",
                level = CyberpunkTitleLevel.H1
            )
            CyberpunkTitle(
                text = "Medium Title",
                level = CyberpunkTitleLevel.H2
            )
            CyberpunkTitle(
                text = "Small Title", 
                level = CyberpunkTitleLevel.H3
            )
        }
    }
}

@Preview
@Composable
fun CPCardPreview() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .padding(16.dp)
        ) {
            CyberpunkCard(
                modifier = Modifier.width(300.dp)
            ) {
                CyberpunkTitle(
                    text = "Card Title",
                    level = CyberpunkTitleLevel.H3
                )
                Spacer(modifier = Modifier.height(8.dp))
                CyberPunkParagraph(
                    text = "This is a card with some content inside. It has a nice cyberpunk style border."
                )
            }
        }
    }
}

@Preview
@Composable
fun CPDialogPreview() {
    CyberpunkTheme {
        var showDialog by remember { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .background(CyberpunkTheme.colors.yellowPrimary)
                .size(400.dp),
            contentAlignment = Alignment.Center
        ) {
            if (showDialog) {
                CyberpunkDialog(
                    onDismiss = { showDialog = false },
                    title = "Warning"
                ) {
                    CyberPunkParagraph(
                        text = "This is a cyberpunk styled dialog",
                        style = ParagraphStyle.NORMAL
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        CyberpunkButton(
                            text = "OK",
                            onClick = { showDialog = false },
                            variant = CyberpunkButtonVariant.Green
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AllComponentsPreview() {
    CyberpunkTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.yellowPrimary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Atoms
                CyberpunkTitle(text = "Cyberpunk Components", level = CyberpunkTitleLevel.H2)

                CyberPunkSeparator()

                CyberpunkTitle(text = "Atoms", level = CyberpunkTitleLevel.H2)

                CyberpunkButton(text = "Primary Button", onClick = {}, variant = CyberpunkButtonVariant.Red)
                
                var textValue by remember { mutableStateOf("") }
                CyberpunkTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    placeholder = "Input Field"
                )
                
                var checked by remember { mutableStateOf(false) }
                CyberpunkCheckbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    label = "Checkbox"
                )
                
                var selected by remember { mutableStateOf(false) }
                CyberpunkRadioButton(
                    selected = selected,
                    onClick = { selected = !selected },
                    label = "Radio Button"
                )

                CyberPunkLink(
                    text = "Clickable Link",
                    onClick = {}
                )

                CyberPunkSeparator(isGlitched = true)
                
                // Molecules
                CyberpunkTitle(text = "Molecules", level = CyberpunkTitleLevel.H2)

                CyberpunkCard {
                    CyberPunkParagraph(
                        text = "This is a card component with a paragraph inside."
                    )
                }
                
                CyberPunkList(
                    items = listOf("Item 1", "Item 2", "Item 3"),
                    listType = ListType.UNORDERED
                )
                
                var selectedOption by remember { mutableStateOf("") }
                CyberpunkDropdown(
                    options = listOf("Option 1", "Option 2", "Option 3"),
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it },
                    label = "Select Option"
                )
                
                // Organisms
                CyberpunkTitle(text = "Organisms", level = CyberpunkTitleLevel.H2)

                CyberPunkSection(
                    style = SectionStyle.BLACK,
                    border = SectionBorder.BOTH
                ) {
                    CyberPunkParagraph(
                        text = "This is a section with red styling and top border."
                    )
                }
            }
        }
    }
}