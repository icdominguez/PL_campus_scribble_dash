package com.icdominguez.scribbledash.ui.screens.statistics.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography

@Composable
fun StatisticsItem(
    icon: Int,
    text: String,
    result: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                color = LocalScribbleDashColorsPalette.current.surfaceHigh,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(
                start = 12.dp,
                end = 20.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(start = 12.dp),
            text = text,
            style = LocalScribbleDashTypography.current.bodySmall.copy(
                color = LocalScribbleDashColorsPalette.current.onBackgroundVariant,
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = result,
            style = LocalScribbleDashTypography.current.headlineLarge.copy(
                color = LocalScribbleDashColorsPalette.current.onBackground,
            )
        )
    }
}

@Preview
@Composable
private fun StatisticsItemPreview() {
    StatisticsItem(
        icon = R.drawable.stat_hourglass,
        text = "Nothing to track ... for now",
        result = "0%"
    )
}