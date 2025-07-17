package tech.vivienne.v_page2.design.molecules

import tech.vivienne.v_page2.design.CyberpunkTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.CyberpunkShapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CyberpunkDropdown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "Select option",
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    leadingContent: @Composable (() -> Unit)? = null
) {
    val colors = CyberpunkTheme.colors
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = CyberpunkTheme.typography.bodyMedium,
                color = if (isError) colors.redPrimary else colors.blackPrimary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it && enabled }
        ) {
            OutlinedTextField(
                value = selectedOption.ifEmpty { placeholder },
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded,
                        modifier = Modifier.menuAnchor()
                    )
                },
                leadingIcon = leadingContent,
                modifier = Modifier
                    .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                    .fillMaxWidth()
                    .clip(CyberpunkShapes.InputFieldShape)
                    .border(
                        width = if (isError) 2.dp else 1.dp,
                        color = if (isError) colors.redPrimary else colors.blackPrimary,
                        shape = CyberpunkShapes.InputFieldShape
                    ),
                enabled = enabled,
                isError = isError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = if (selectedOption.isEmpty())
                        colors.blackPrimary.copy(alpha = 0.6f) else colors.blackPrimary,
                    unfocusedTextColor = if (selectedOption.isEmpty())
                        colors.blackPrimary.copy(alpha = 0.6f) else colors.blackPrimary,
                    disabledTextColor = colors.blackPrimary.copy(alpha = 0.5f),
                    errorTextColor = colors.redPrimary,
                    focusedBorderColor = colors.blackPrimary,
                    unfocusedBorderColor = colors.blackPrimary,
                    disabledBorderColor = colors.blackPrimary.copy(alpha = 0.5f),
                    errorBorderColor = colors.redPrimary,
                    focusedContainerColor = colors.yellowPrimary,
                    unfocusedContainerColor = colors.yellowPrimary,
                    disabledContainerColor = colors.yellowPrimary.copy(alpha = 0.5f),
                    errorContainerColor = colors.yellowPrimary,
                    focusedTrailingIconColor = colors.blackPrimary,
                    unfocusedTrailingIconColor = colors.blackPrimary,
                    disabledTrailingIconColor = colors.blackPrimary.copy(alpha = 0.5f),
                    errorTrailingIconColor = colors.redPrimary,
                    focusedLeadingIconColor = colors.blackPrimary,
                    unfocusedLeadingIconColor = colors.blackPrimary,
                    disabledLeadingIconColor = colors.blackPrimary.copy(alpha = 0.5f),
                    errorLeadingIconColor = colors.redPrimary
                ),
                textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                shape = CyberpunkShapes.InputFieldShape
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(colors.blackPrimary)
                    .border(
                        width = 2.dp,
                        color = colors.borderGreen,
                        shape = CyberpunkShapes.InputFieldShape
                    )
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                style = CyberpunkTheme.typography.bodyMedium,
                                color = colors.yellowPrimary,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = colors.yellowPrimary,
                            leadingIconColor = colors.yellowPrimary,
                            trailingIconColor = colors.yellowPrimary
                        ),
                        modifier = Modifier.background(
                            if (selectedOption == option)
                                colors.borderGreen.copy(alpha = 0.2f)
                            else colors.blackPrimary
                        )
                    )
                }
            }
        }

        supportingText?.let { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 4.dp)
            ) {
                Text(
                    text = text,
                    style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = if (isError) colors.redPrimary else colors.blackPrimary.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CyberpunkMultiSelectDropdown(
    options: List<String>,
    selectedOptions: List<String>,
    onOptionsSelected: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "Select options",
    enabled: Boolean = true,
    maxSelections: Int = Int.MAX_VALUE
) {
    val colors = CyberpunkTheme.colors
    var expanded by remember { mutableStateOf(false) }

    val displayText = when {
        selectedOptions.isEmpty() -> placeholder
        selectedOptions.size == 1 -> selectedOptions.first()
        else -> "${selectedOptions.size} selected"
    }

    Column(modifier = modifier) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = CyberpunkTheme.typography.bodyMedium,
                color = colors.blackPrimary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it && enabled }
        ) {
            OutlinedTextField(
                value = displayText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded,
                        modifier = Modifier.menuAnchor()
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .clip(CyberpunkShapes.InputFieldShape),
                enabled = enabled,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = if (selectedOptions.isEmpty())
                        colors.blackPrimary.copy(alpha = 0.6f) else colors.blackPrimary,
                    unfocusedTextColor = if (selectedOptions.isEmpty())
                        colors.blackPrimary.copy(alpha = 0.6f) else colors.blackPrimary,
                    disabledTextColor = colors.blackPrimary.copy(alpha = 0.5f),
                    focusedBorderColor = colors.blackPrimary,
                    unfocusedBorderColor = colors.blackPrimary,
                    disabledBorderColor = colors.blackPrimary.copy(alpha = 0.5f),
                    focusedContainerColor = colors.yellowPrimary,
                    unfocusedContainerColor = colors.yellowPrimary,
                    disabledContainerColor = colors.yellowPrimary.copy(alpha = 0.5f),
                    focusedTrailingIconColor = colors.blackPrimary,
                    unfocusedTrailingIconColor = colors.blackPrimary,
                    disabledTrailingIconColor = colors.blackPrimary.copy(alpha = 0.5f)
                ),
                textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                shape = CyberpunkShapes.InputFieldShape
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(colors.blackPrimary)
                    .border(
                        width = 2.dp,
                        color = colors.borderGreen,
                        shape = CyberpunkShapes.InputFieldShape
                    )
            ) {
                options.forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    val canSelect = selectedOptions.size < maxSelections || isSelected

                    DropdownMenuItem(
                        text = {
                            Row {
                                Text(
                                    text = if (isSelected) "✓ " else "",
                                    color = colors.borderGreen,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = option,
                                    style = CyberpunkTheme.typography.bodyMedium,
                                    color = if (canSelect) colors.yellowPrimary else colors.yellowPrimary.copy(alpha = 0.5f),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        },
                        onClick = {
                            if (canSelect) {
                                val newSelection = if (isSelected) {
                                    selectedOptions - option
                                } else {
                                    selectedOptions + option
                                }
                                onOptionsSelected(newSelection)
                            }
                        },
                        enabled = canSelect,
                        colors = MenuDefaults.itemColors(
                            textColor = colors.yellowPrimary,
                            disabledTextColor = colors.yellowPrimary.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.background(
                            if (isSelected)
                                colors.borderGreen.copy(alpha = 0.2f)
                            else colors.blackPrimary
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CyberpunkDropdownPreview() {
    CyberpunkTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var selectedProtocol by remember { mutableStateOf("") }
            CyberpunkDropdown(
                options = listOf("TCP", "UDP", "ICMP", "HTTP", "HTTPS"),
                selectedOption = selectedProtocol,
                onOptionSelected = { selectedProtocol = it },
                label = "Network Protocol",
                placeholder = "Select protocol",
                supportingText = "Choose connection type"
            )

            var selectedSecurity by remember { mutableStateOf("Medium") }
            CyberpunkDropdown(
                options = listOf("Low", "Medium", "High", "Maximum"),
                selectedOption = selectedSecurity,
                onOptionSelected = { selectedSecurity = it },
                label = "Security Level",
                supportingText = "Current: $selectedSecurity"
            )

            var selectedError by remember { mutableStateOf("") }
            CyberpunkDropdown(
                options = listOf("Option 1", "Option 2", "Option 3"),
                selectedOption = selectedError,
                onOptionSelected = { selectedError = it },
                label = "Error State",
                placeholder = "Invalid selection",
                isError = true,
                supportingText = "Selection required"
            )

            var selectedDisabled by remember { mutableStateOf("Disabled") }
            CyberpunkDropdown(
                options = listOf("Option 1", "Option 2", "Option 3"),
                selectedOption = selectedDisabled,
                onOptionSelected = { selectedDisabled = it },
                label = "Disabled Dropdown",
                enabled = false,
                supportingText = "Currently unavailable"
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkDropdownDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var selectedConnection by remember { mutableStateOf("") }
            CyberpunkDropdown(
                options = listOf("Neural Link", "Wireless", "Ethernet", "Quantum"),
                selectedOption = selectedConnection,
                onOptionSelected = { selectedConnection = it },
                label = "Connection Type",
                placeholder = "Select connection"
        )

            var selectedTargets by remember { mutableStateOf(listOf<String>()) }
            CyberpunkMultiSelectDropdown(
                options = listOf("Database", "Network", "Security", "Firewall", "Mainframe"),
                selectedOptions = selectedTargets,
                onOptionsSelected = { selectedTargets = it },
                label = "Breach Targets",
                placeholder = "Select targets",
                maxSelections = 3
            )
        }
    }
}