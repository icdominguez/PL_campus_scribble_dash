package com.icdominguez.scribbledash.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.model.GameMode
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.composables.GameModeComponent
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography

@Composable
fun HomeScreen(
    state: HomeScreenViewModel.State = HomeScreenViewModel.State(),
    uiEvent: (HomeScreenViewModel.Event) -> Unit = {},
    navigateToSelectDifficultyScreen: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        LocalScribbleDashColorsPalette.current.backgroundGradientA,
                        LocalScribbleDashColorsPalette.current.backgroundGradientB,
                    ),
                )
            ),
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 20.dp,
                    bottom = 20.dp,
                ),
            text = "ScribbleDash",
            style = LocalScribbleDashTypography.current.headlineMedium.copy(
                color = LocalScribbleDashColorsPalette.current.onBackground,
            )
        )

        Spacer(modifier = Modifier.height(84.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.start_drawing),
                style = LocalScribbleDashTypography.current.displayMedium.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackground
                )
            )

            Text(
                text = stringResource(R.string.select_game_mode),
                style = LocalScribbleDashTypography.current.bodyMedium.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackgroundVariant,
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
             modifier = Modifier
                 .padding(horizontal = 16.dp)
        ) {
            items(GameMode.entries.size) { gameModeIndex ->
                GameModeComponent(
                    gameMode = GameMode.entries[gameModeIndex].stringId,
                    gameIcon = GameMode.entries[gameModeIndex].icon,
                    onClick = { navigateToSelectDifficultyScreen() }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}