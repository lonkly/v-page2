package tech.vivienne.v_page2.design

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape

object CyberpunkShapes {
    val ButtonShape = GenericShape { size, _ ->
        val path = Path().apply {
            // Cyberpunk button clip path
            moveTo(-15f, 0f)
            lineTo(size.width + 15f, 0f)
            lineTo(size.width + 15f, size.height)
            lineTo(20f, size.height)
            lineTo(-15f, size.height - 35f)
            close()
        }
        addPath(path)
    }

    val InputFieldShape = GenericShape { size, _ ->
        val path = Path().apply {
            // Input field clip path
            moveTo(0f, 25f)
            lineTo(26f, 0f)
            lineTo(size.width * 0.6f - 25f, 0f)
            lineTo(size.width * 0.6f, 25f)
            lineTo(size.width, 25f)
            lineTo(size.width, size.height - 10f)
            lineTo(size.width - 15f, size.height - 10f)
            lineTo(size.width * 0.8f - 10f, size.height - 10f)
            lineTo(size.width * 0.8f - 15f, size.height)
            lineTo(10f, size.height)
            lineTo(0f, size.height - 10f)
            close()
        }
        addPath(path)
    }

    val CardShape = GenericShape { size, _ ->
        val path = Path().apply {
            // Card/Section clip path
            moveTo(0f, size.height * 0.3f)
            lineTo(30f, size.height * 0.3f + 30f)
            lineTo(30f, size.height - 30f)
            lineTo(size.width * 0.22f, size.height - 30f)
            lineTo(size.width * 0.22f + 30f, size.height)
            lineTo(size.width * 0.52f, size.height)
            lineTo(size.width * 0.52f + 15f, size.height - 15f)
            lineTo(size.width * 0.7f, size.height - 15f)
            lineTo(size.width * 0.7f + 15f, size.height)
            lineTo(size.width * 0.9f, size.height)
            lineTo(size.width * 0.9f + 30f, size.height - 30f)
            lineTo(size.width, size.height - 30f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        addPath(path)
    }

    val ImageFrameShape = GenericShape { size, _ ->
        val path = Path().apply {
            // Image frame clip path
            moveTo(0f, 25f)
            lineTo(26f, 0f)
            lineTo(size.width * 0.6f - 25f, 0f)
            lineTo(size.width * 0.6f, 25f)
            lineTo(size.width, 25f)
            lineTo(size.width, size.height - 10f)
            lineTo(size.width - 15f, size.height - 10f)
            lineTo(size.width * 0.8f - 10f, size.height - 10f)
            lineTo(size.width * 0.8f - 15f, size.height)
            lineTo(10f, size.height)
            lineTo(0f, size.height - 10f)
            close()
        }
        addPath(path)
    }
}