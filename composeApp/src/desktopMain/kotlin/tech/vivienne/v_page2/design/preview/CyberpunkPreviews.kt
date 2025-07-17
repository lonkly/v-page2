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
            CPButton(
                text = "Primary Button",
                onClick = {},
                style = ButtonStyle.PRIMARY
            )
            CPButton(
                text = "Secondary Button",
                onClick = {},
                style = ButtonStyle.SECONDARY
            )
            CPButton(
                text = "Danger Button",
                onClick = {},
                style = ButtonStyle.DANGER
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
            CPTextField(
                value = text,
                onValueChange = { text = it },
                label = "Username"
            )
            CPTextField(
                value = text,
                onValueChange = { text = it },
                label = "Password",
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
            CPCheckbox(
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
            CPTitle(
                text = "Large Title",
                size = TitleSize.LARGE
            )
            CPTitle(
                text = "Medium Title",
                size = TitleSize.MEDIUM
            )
            CPTitle(
                text = "Small Title", 
                size = TitleSize.SMALL
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
            CPCard(
                modifier = Modifier.width(300.dp)
            ) {
                CPTitle(
                    text = "Card Title",
                    size = TitleSize.SMALL
                )
                Spacer(modifier = Modifier.height(8.dp))
                CPParagraph(
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
                CPAlertDialog(
                    title = "Warning",
                    message = "This is a cyberpunk styled alert dialog",
                    onDismiss = { showDialog = false },
                    onConfirm = { showDialog = false }
                )
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
                CPTitle(text = "Cyberpunk Components", size = TitleSize.LARGE)
                
                CPSeparator()
                
                CPTitle(text = "Atoms", size = TitleSize.MEDIUM)
                
                CPButton(text = "Primary Button", onClick = {})
                
                var textValue by remember { mutableStateOf("") }
                CPTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    label = "Input Field"
                )
                
                var checked by remember { mutableStateOf(false) }
                CPCheckbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    label = "Checkbox"
                )
                
                var selected by remember { mutableStateOf(false) }
                CPRadioButton(
                    selected = selected,
                    onClick = { selected = !selected },
                    label = "Radio Button"
                )
                
                CPLink(
                    text = "Clickable Link",
                    onClick = {}
                )
                
                CPSeparator(isGlitched = true)
                
                // Molecules
                CPTitle(text = "Molecules", size = TitleSize.MEDIUM)
                
                CPCard {
                    CPParagraph(
                        text = "This is a card component with a paragraph inside."
                    )
                }
                
                CPList(
                    items = listOf("Item 1", "Item 2", "Item 3"),
                    listType = ListType.UNORDERED
                )
                
                var selectedOption by remember { mutableStateOf("") }
                CPDropDown(
                    options = listOf("Option 1", "Option 2", "Option 3"),
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it },
                    label = "Select Option"
                )
                
                // Organisms
                CPTitle(text = "Organisms", size = TitleSize.MEDIUM)
                
                CPSection(
                    style = SectionStyle.RED,
                    border = SectionBorder.TOP
                ) {
                    CPParagraph(
                        text = "This is a section with red styling and top border."
                    )
                }
            }
        }
    }
}