package tech.vivienne.v_page2.design.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.vivienne.v_page2.design.CyberpunkTheme
import tech.vivienne.v_page2.design.CyberpunkShapes
import tech.vivienne.v_page2.design.ScanDirection
import tech.vivienne.v_page2.design.atoms.CyberpunkButton
import tech.vivienne.v_page2.design.atoms.CyberpunkButtonVariant
import tech.vivienne.v_page2.design.atoms.CyberpunkLoadingSpinner
import tech.vivienne.v_page2.design.atoms.CyberpunkTextField
import tech.vivienne.v_page2.design.glitchEffect
import tech.vivienne.v_page2.design.scanningEffect

@Composable
fun CyberpunkDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: ImageVector? = null,
    dismissible: Boolean = true,
    glitchEffect: Boolean = false,
    scanningEffect: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = CyberpunkTheme.colors

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dismissible,
            dismissOnClickOutside = dismissible
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clip(CyberpunkShapes.CardShape)
                .background(colors.blackPrimary)
                .border(
                    width = 2.dp,
                    color = colors.borderGreen,
                    shape = RectangleShape
                )
                .glitchEffect(isActive = glitchEffect, intensity = 0.5f)
                .scanningEffect(
                    isActive = scanningEffect,
                    direction = ScanDirection.Horizontal,
                    color = colors.borderGreen
                ),
            colors = CardDefaults.cardColors(
                containerColor = colors.blackPrimary,
                contentColor = colors.yellowPrimary
            ),
            shape = CyberpunkShapes.CardShape
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                // Header
                if (title != null || icon != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        icon?.let { iconVector ->
                            Icon(
                                imageVector = iconVector,
                                contentDescription = null,
                                tint = colors.borderGreen,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }

                        title?.let { titleText ->
                            Text(
                                text = titleText,
                                style = CyberpunkTheme.typography.titleMedium,
                                color = colors.yellowPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Content
                content()
            }
        }
    }
}

@Composable
fun CyberpunkConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "Confirm",
    dismissText: String = "Cancel",
    confirmVariant: CyberpunkButtonVariant = CyberpunkButtonVariant.Red,
    dismissVariant: CyberpunkButtonVariant = CyberpunkButtonVariant.Blue,
    icon: ImageVector? = null,
    urgent: Boolean = false
) {
    CyberpunkDialog(
        onDismiss = onDismiss,
        modifier = modifier,
        title = title,
        icon = icon,
        glitchEffect = urgent,
        scanningEffect = urgent
    ) {
        Text(
            text = message,
            style = CyberpunkTheme.typography.bodyMedium,
            color = CyberpunkTheme.colors.yellowPrimary,
            lineHeight = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkButton(
                onClick = onDismiss,
                text = dismissText,
                variant = dismissVariant,
                modifier = Modifier.weight(1f)
            )

            CyberpunkButton(
                onClick = onConfirm,
                text = confirmText,
                variant = confirmVariant,
                modifier = Modifier.weight(1f),
                glitchEffect = urgent
            )
        }
    }
}

@Composable
fun CyberpunkInputDialog(
    title: String,
    placeholder: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    initialValue: String = "",
    confirmText: String = "Submit",
    dismissText: String = "Cancel",
    isPassword: Boolean = false,
    validation: ((String) -> String?)? = null,
    icon: ImageVector? = null
) {
    var inputValue by remember { mutableStateOf(initialValue) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(inputValue) {
        errorMessage = validation?.invoke(inputValue)
    }

    CyberpunkDialog(
        onDismiss = onDismiss,
        modifier = modifier,
        title = title,
        icon = icon
    ) {
        CyberpunkTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            placeholder = placeholder,
            isPassword = isPassword,
            isError = errorMessage != null,
            supportingText = errorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CyberpunkButton(
                onClick = onDismiss,
                text = dismissText,
                variant = CyberpunkButtonVariant.Blue,
                modifier = Modifier.weight(1f)
            )

            CyberpunkButton(
                onClick = { onConfirm(inputValue) },
                text = confirmText,
                variant = CyberpunkButtonVariant.Green,
                enabled = errorMessage == null && inputValue.isNotBlank(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CyberpunkLoadingDialog(
    message: String,
    onDismiss: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    progress: Float? = null,
    icon: ImageVector? = null
) {
    CyberpunkDialog(
        onDismiss = onDismiss ?: {},
        modifier = modifier,
        dismissible = onDismiss != null,
        scanningEffect = true
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            icon?.let { iconVector ->
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = CyberpunkTheme.colors.borderGreen,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(bottom = 16.dp)
                )
            }

            CyberpunkLoadingSpinner(
                size = 60.dp,
                color = CyberpunkTheme.colors.borderGreen,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = message,
                style = CyberpunkTheme.typography.bodyMedium,
                color = CyberpunkTheme.colors.yellowPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            progress?.let { progressValue ->
                LinearProgressIndicator(
                progress = { progressValue },
                modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                color = CyberpunkTheme.colors.borderGreen,
                trackColor = CyberpunkTheme.colors.blackPrimary,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )

                Text(
                    text = "${(progressValue * 100).toInt()}%",
                    style = CyberpunkTheme.typography.bodyMedium,
                    color = CyberpunkTheme.colors.borderGreen,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CyberpunkErrorDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    actionText: String = "OK",
    onAction: (() -> Unit)? = null,
    details: String? = null
) {
    var showDetails by remember { mutableStateOf(false) }

    CyberpunkDialog(
        onDismiss = onDismiss,
        modifier = modifier,
        title = title,
        icon = Icons.Default.Error,
        glitchEffect = true
    ) {
        Text(
            text = message,
            style = CyberpunkTheme.typography.bodyMedium,
            color = CyberpunkTheme.colors.redPrimary,
            lineHeight = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        details?.let { detailsText ->
            CyberpunkButton(
                onClick = { showDetails = !showDetails },
                text = if (showDetails) "Hide Details" else "Show Details",
                variant = CyberpunkButtonVariant.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            if (showDetails) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CyberpunkTheme.colors.blackPrimary.copy(alpha = 0.5f)
                    )
                ) {
                    Text(
                        text = detailsText,
                        style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        color = CyberpunkTheme.colors.yellowPrimary.copy(alpha = 0.8f),
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (onAction != null) {
                CyberpunkButton(
                    onClick = onAction,
                    text = actionText,
                    variant = CyberpunkButtonVariant.Red,
                    modifier = Modifier.weight(1f)
                )

                CyberpunkButton(
                    onClick = onDismiss,
                    text = "Cancel",
                    variant = CyberpunkButtonVariant.Blue,
                    modifier = Modifier.weight(1f)
                )
            } else {
                CyberpunkButton(
                    onClick = onDismiss,
                    text = actionText,
                    variant = CyberpunkButtonVariant.Red,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CyberpunkSuccessDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    actionText: String = "OK",
    onAction: (() -> Unit)? = null
) {
    CyberpunkDialog(
        onDismiss = onDismiss,
        modifier = modifier,
        title = title,
        icon = Icons.Default.CheckCircle
    ) {
        Text(
            text = message,
            style = CyberpunkTheme.typography.bodyMedium,
            color = CyberpunkTheme.colors.greenPrimary,
            lineHeight = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (onAction != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CyberpunkButton(
                    onClick = onDismiss,
                    text = "Close",
                    variant = CyberpunkButtonVariant.Blue,
                    modifier = Modifier.weight(1f)
                )

                CyberpunkButton(
                    onClick = onAction,
                    text = actionText,
                    variant = CyberpunkButtonVariant.Green,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            CyberpunkButton(
                onClick = onDismiss,
                text = actionText,
                variant = CyberpunkButtonVariant.Green,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkDialogPreview() {
    CyberpunkTheme {
        var showDialog by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.yellowPrimary),
            contentAlignment = Alignment.Center
        ) {
            CyberpunkButton(
                onClick = { showDialog = true },
                text = "Show Dialog"
            )
        }

        if (showDialog) {
            CyberpunkConfirmationDialog(
                title = "Breach Firewall",
                message = "This action will attempt to breach the target's firewall. This may trigger security protocols and alert the target system. Are you sure you want to proceed?",
                onConfirm = { showDialog = false },
                onDismiss = { showDialog = false },
                confirmText = "Breach",
                dismissText = "Abort",
                icon = Icons.Default.Security,
                urgent = true
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkInputDialogPreview() {
    CyberpunkTheme {
        var showDialog by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.yellowPrimary),
            contentAlignment = Alignment.Center
        ) {
            CyberpunkButton(
                onClick = { showDialog = true },
                text = "Show Input Dialog"
            )
        }

        if (showDialog) {
            CyberpunkInputDialog(
                title = "Enter Access Code",
                placeholder = "Neural access code",
                onConfirm = { code ->
                    // Handle the input
                    showDialog = false
                },
                onDismiss = { showDialog = false },
                icon = Icons.Default.Lock,
                validation = { input ->
                    when {
                        input.length < 8 -> "Code must be at least 8 characters"
                        !input.any { it.isDigit() } -> "Code must contain at least one digit"
                        else -> null
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkLoadingDialogPreview() {
    CyberpunkTheme {
        var showDialog by remember { mutableStateOf(true) }
        var progress by remember { mutableStateOf(0.65f) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.yellowPrimary),
            contentAlignment = Alignment.Center
        ) {
            CyberpunkButton(
                onClick = { showDialog = true },
                text = "Show Loading Dialog"
            )
        }

        if (showDialog) {
            CyberpunkLoadingDialog(
                message = "Establishing neural connection...",
                progress = progress,
                onDismiss = { showDialog = false },
                icon = Icons.Default.Cable
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkErrorDialogPreview() {
    CyberpunkTheme {
        var showDialog by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.yellowPrimary),
            contentAlignment = Alignment.Center
        ) {
            CyberpunkButton(
                onClick = { showDialog = true },
                text = "Show Error Dialog"
            )
        }

        if (showDialog) {
            CyberpunkErrorDialog(
                title = "Connection Failed",
                message = "Unable to establish neural link with the target system. The connection was terminated unexpectedly.",
                onDismiss = { showDialog = false },
                actionText = "Retry",
                onAction = {
                    // Retry logic
                    showDialog = false
                },
                details = "Error Code: 0x2077\nTimestamp: 2077-12-10 23:45:12\nTarget: corp.arasaka.net\nReason: ICE_DETECTED"
            )
        }
    }
}

@Preview
@Composable
fun CyberpunkDialogDarkPreview() {
    CyberpunkTheme(darkTheme = true) {
        var showDialog by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CyberpunkTheme.colors.blackPrimary),
            contentAlignment = Alignment.Center
        ) {
            CyberpunkButton(
                onClick = { showDialog = true },
                text = "Show Success Dialog"
            )
        }

        if (showDialog) {
            CyberpunkSuccessDialog(
                title = "Hack Successful",
                message = "Successfully breached the target system. All data has been extracted and the connection has been severed without detection.",
                onDismiss = { showDialog = false },
                actionText = "Continue",
                onAction = { showDialog = false }
            )
        }
    }
}