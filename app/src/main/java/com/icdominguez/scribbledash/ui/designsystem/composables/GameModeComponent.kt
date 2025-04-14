package com.icdominguez.scribbledash.ui.designsystem.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography

@Composable
fun GameModeComponent(
    gameMode: Int,
    gameIcon: Int,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 8.dp,
                color = LocalScribbleDashColorsPalette.current.success,
                shape = RoundedCornerShape(20.dp),
            )
            .background(color = LocalScribbleDashColorsPalette.current.surfaceHigh)
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .weight(1f),
                text = stringResource(gameMode),
                style = LocalScribbleDashTypography.current.headlineMedium.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackgroundVariant,
                )
            )

            Image(
                painter = painterResource(gameIcon),
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameModeComponentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp),
    ) {
        GameModeComponent(
            gameMode = R.string.one_round_wonder,
            gameIcon = R.drawable.one_round_wonder,
        )
    }
}