package tech.vivienne.v_page2.design.atoms

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import tech.vivienne.v_page2.design.CyberpunkTheme

@Composable
fun CyberPunkFieldset(
    modifier: Modifier = Modifier,
    borderColor: Color = CyberpunkTheme.colors.purplePrimary,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .border(
                width = 3.dp,
                color = borderColor,
                shape = RectangleShape
            )
            .padding(16.dp)
    ) {
        content()
    }
}