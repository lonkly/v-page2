package tech.vivienne.v_page2.design.atoms


import tech.vivienne.v_page2.design.CyberpunkShapes
import tech.vivienne.v_page2.design.CyberpunkTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.vivienne.v_page2.design.hackFontFamily

@Composable
fun CyberpunkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null
) {
    val colors = CyberpunkTheme.colors
    val fontFamily = hackFontFamily()

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(CyberpunkShapes.InputFieldShape)
                .background(colors.yellowPrimary),
            textStyle = CyberpunkTheme.typography.bodyLarge.copy(
                fontFamily = fontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = if (isPassword) 2.sp else 0.sp
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = CyberpunkTheme.typography.bodyLarge.copy(
                        fontFamily = fontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colors.blackPrimary.copy(alpha = 0.6f)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = singleLine,
            maxLines = maxLines,
            enabled = enabled,
            isError = isError,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colors.blackPrimary,
                unfocusedTextColor = colors.blackPrimary,
                disabledTextColor = colors.blackPrimary.copy(alpha = 0.5f),
                errorTextColor = colors.redPrimary,
                focusedBorderColor = colors.blackPrimary,
                unfocusedBorderColor = colors.blackPrimary,
                disabledBorderColor = colors.blackPrimary.copy(alpha = 0.5f),
                errorBorderColor = colors.redPrimary,
                cursorColor = colors.blackPrimary,
                errorCursorColor = colors.redPrimary,
                focusedContainerColor = colors.yellowPrimary,
                unfocusedContainerColor = colors.yellowPrimary,
                disabledContainerColor = colors.yellowPrimary.copy(alpha = 0.5f),
                errorContainerColor = colors.yellowPrimary
            ),
            shape = CyberpunkShapes.InputFieldShape
        )

        supportingText?.let { text ->
            Text(
                text = text,
                style = CyberpunkTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = if (isError) colors.redPrimary else colors.blackPrimary.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

