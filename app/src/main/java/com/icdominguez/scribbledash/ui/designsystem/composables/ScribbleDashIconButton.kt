package com.icdominguez.scribbledash.ui.designsystem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette

@Composable
fun ScribbleDashIconButton(
    isEnabled: Boolean = false,
    icon: Int,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = when {
        isEnabled && isPressed -> LocalScribbleDashColorsPalette.current.surfaceLow
        isEnabled && !isPressed -> LocalScribbleDashColorsPalette.current.surfaceLowest
        else -> LocalScribbleDashColorsPalette.current.surfaceLowest.copy(alpha = 0.4f)
    }

    val iconTint = if(!isEnabled) {
        LocalScribbleDashColorsPalette.current.onBackground.copy(alpha = 0.6f)
    } else {
        LocalScribbleDashColorsPalette.current.onBackground
    }

    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(22.dp),
                color = backgroundColor
            )
            .padding(all = 18.dp)
            .clickable(
                enabled = isEnabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() }
            )
    ) {
        Icon(
            modifier = Modifier
                .size(28.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconTint
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ScribbleDashIconButtonPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        ScribbleDashIconButton(
            isEnabled = false,
            icon = R.drawable.reply,
            onClick = {}
        )

        ScribbleDashIconButton(
            isEnabled = true,
            icon = R.drawable.reply,
            onClick = {}
        )
    }
}