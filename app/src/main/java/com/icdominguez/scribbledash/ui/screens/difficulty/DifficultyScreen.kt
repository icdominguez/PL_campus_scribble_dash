package com.icdominguez.scribbledash.ui.screens.difficulty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.model.Difficulty
import com.icdominguez.scribbledash.ui.designsystem.composables.CloseButton
import com.icdominguez.scribbledash.ui.designsystem.composables.DifficultyButton

@Composable
fun SelectDifficultyScreen(
    navigateBack: () -> Unit = {},
    navigateToDrawScreen: () -> Unit = {},
) {
    Column {
        CloseButton(onClick = navigateBack)
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(36.dp),
            ) {
                Difficulty.entries.map { difficulty ->
                    DifficultyButton(
                        modifier = Modifier
                            .offset(y = (-16).dp),
                        difficulty = difficulty,
                        onClick = { navigateToDrawScreen() },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectDifficultyScreenPreview() {
    SelectDifficultyScreen()
}