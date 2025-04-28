package com.icdominguez.scribbledash.ui.designsystem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography

@Composable
fun ScribbleDashButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    onClick: () -> Unit,
    color: Color = LocalScribbleDashColorsPalette.current.primary,
    text: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(20.dp),
                color = LocalScribbleDashColorsPalette.current.surfaceHigh
            )
            .padding(all = 6.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = if(isEnabled) color else LocalScribbleDashColorsPalette.current.surfaceLowest
                )
                .clickable(
                    enabled = isEnabled,
                    onClick = onClick
                )
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text.uppercase(),
                style = LocalScribbleDashTypography.current.headlineSmall.copy(
                    color = LocalScribbleDashColorsPalette.current.surfaceHigh,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScribbleDashButtonPreview() {
    Column(
        modifier = Modifier
            .background(LocalScribbleDashColorsPalette.current.onSurface),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ScribbleDashButton(
            isEnabled = true,
            onClick = {},
            text = "Start!"
        )

        ScribbleDashButton(
            isEnabled = false,
            onClick = {},
            text = "Start!"
        )
    }
}