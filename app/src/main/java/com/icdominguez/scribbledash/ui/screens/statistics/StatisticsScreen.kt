package com.icdominguez.scribbledash.ui.screens.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography
import com.icdominguez.scribbledash.ui.screens.statistics.composables.StatisticsItem

@Composable
fun Statistics() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 22.dp,
                    bottom = 22.dp
                )
        ) {
            Text(
                text = "Statistics",
                style = LocalScribbleDashTypography.current.labelXLarge.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackground,
                )
            )
        }

        Column(
            modifier = Modifier
                .padding(
                    top = 19.dp,
                    start = 16.dp,
                    end = 16.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatisticsItem(
                icon = R.drawable.stat_hourglass,
                text = "Nothing to track...for now",
                result = "0%"
            )
            StatisticsItem(
                icon = R.drawable.stat_bolt,
                text = "Nothing to track...for now",
                result = "0"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChartScreenPreview() {
    Statistics()
}