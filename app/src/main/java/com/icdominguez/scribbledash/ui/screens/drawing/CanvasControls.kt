package com.icdominguez.scribbledash.ui.screens.drawing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.composables.ScribbleDashButton
import com.icdominguez.scribbledash.ui.designsystem.composables.ScribbleDashIconButton
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette

@Composable
fun CanvasControls(
    modifier: Modifier = Modifier,
    onClearCanvas: () -> Unit = {},
    isClearCanvasButtonEnabled: Boolean = false,
    onUndoButtonClick: () -> Unit = {},
    onRedoButtonClick: () -> Unit = {},
    isUndoButtonEnabled: Boolean = false,
    isRedoButtonEnabled: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ScribbleDashIconButton(
            isEnabled = isUndoButtonEnabled,
            icon = R.drawable.reply,
            onClick = { onUndoButtonClick() }
        )

        ScribbleDashIconButton(
            isEnabled = isRedoButtonEnabled,
            icon = R.drawable.forward,
            onClick = { onRedoButtonClick() }
        )

        ScribbleDashButton(
            onClick = {
                onClearCanvas()
            },
            color = LocalScribbleDashColorsPalette.current.success,
            isEnabled = isClearCanvasButtonEnabled,
            text = "Clear Canvas".uppercase()
        )
    }
}

@Preview
@Composable
private fun CanvasControlsPreview() {
    Column {
        CanvasControls(

        )
    }
}