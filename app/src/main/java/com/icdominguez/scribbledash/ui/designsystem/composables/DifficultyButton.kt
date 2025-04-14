package com.icdominguez.scribbledash.ui.designsystem.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.model.Difficulty
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography

@Composable
fun DifficultyButton(
    modifier: Modifier = Modifier,
    difficulty: Difficulty,
    onClick: () -> Unit = {},
) {
    val alignment = when(difficulty) {
        Difficulty.BEGINNER -> Alignment.TopEnd
        Difficulty.CHALLENGING -> Alignment.BottomCenter
        Difficulty.MASTER -> Alignment.Center
    }

    val verticalOffset = when(difficulty) {
        Difficulty.CHALLENGING -> -(16).dp
        else -> 0.dp
    }

    Column(
        modifier = modifier
            .offset(y = verticalOffset)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = CircleShape,
                )
                .background(
                    color = LocalScribbleDashColorsPalette.current.surfaceHigh,
                    shape = CircleShape,
                )
                .size(88.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(alignment),
                painter = painterResource(difficulty.icon),
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 12.dp),
            text = stringResource(difficulty.stringId),
            style = LocalScribbleDashTypography.current.labelMedium.copy(
                color = LocalScribbleDashColorsPalette.current.onBackgroundVariant,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DifficultyButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            Difficulty.entries.map {
                DifficultyButton(
                    modifier = Modifier
                        .offset(y = (-16).dp),
                    difficulty = it,
                )
            }
        }
    }
}