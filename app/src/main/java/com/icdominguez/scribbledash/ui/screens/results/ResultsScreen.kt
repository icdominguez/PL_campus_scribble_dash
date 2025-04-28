package com.icdominguez.scribbledash.ui.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.composables.CloseButton
import com.icdominguez.scribbledash.ui.designsystem.composables.ScribbleDashButton
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography

@Composable
fun ResultsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "100%",
                style = LocalScribbleDashTypography.current.displayLarge.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackground,
                )
            )

            Text(
                text = "Wohoo!",
                style = LocalScribbleDashTypography.current.headlineLarge.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackground,
                )
            )

            Text(
                textAlign = TextAlign.Center,
                text = "You’ve officially raised the bar!\n" +
                        "I’m going to need a ladder to reach it!",
                style = LocalScribbleDashTypography.current.bodyMedium.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackgroundVariant,
                )
            )
        }
        CloseButton(
            modifier = Modifier
                .align(Alignment.TopCenter)
        )

        ScribbleDashButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = stringResource(R.string.try_again),
            onClick = { /*TODO*/ },
            color = LocalScribbleDashColorsPalette.current.primary,
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun ResultsScreenPreview() {
    ResultsScreen()
}