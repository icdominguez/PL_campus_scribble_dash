package com.icdominguez.scribbledash.ui.designsystem.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = modifier
                .size(32.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onClick() }
                ),
            painter = painterResource(R.drawable.close_circle),
            contentDescription = null,
            tint = LocalScribbleDashColorsPalette.current.onSurface,
        )
    }
}

@Preview
@Composable
private fun CloseButtonPreview() {
    CloseButton()
}