package com.icdominguez.scribbledash.ui.screens.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.composables.CloseButton
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashTypography
import kotlin.math.abs

@Composable
fun DrawingScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {
    val viewModel = viewModel<DrawingViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {

        CloseButton(onClick = navigateBack)

        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.start_drawing),
                style = LocalScribbleDashTypography.current.displayMedium.copy(
                    color = LocalScribbleDashColorsPalette.current.onBackground,
                )
            )
            DrawingCanvas(
                paths = state.paths,
                currentPath = state.currentPath,
                onAction = viewModel::onAction,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        CanvasControls(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            isClearCanvasButtonEnabled = state.paths.isNotEmpty(),
            onClearCanvas = {
                viewModel.onAction(DrawingAction.OnClearCanvasClick)
            },
            isUndoButtonEnabled = state.undoPaths.isNotEmpty(),
            isRedoButtonEnabled = state.redoPaths.isNotEmpty(),
            onUndoButtonClick = { viewModel.onAction(DrawingAction.OnUndoButtonClick) },
            onRedoButtonClick = { viewModel.onAction(DrawingAction.OnRedoButtonClick) },
        )
    }
}

@Preview
@Composable
private fun DrawingScreenPreview() {
    DrawingScreen()
}

@Composable
fun ColumnScope.DrawingCanvas(
    modifier: Modifier = Modifier,
    paths: List<PathData>,
    currentPath: PathData?,
    onAction: (DrawingAction) -> Unit,
) {
    val linesColor = LocalScribbleDashColorsPalette.current.onSurfaceVariant

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(
                top = 32.dp,
                start = 29.dp,
                end = 29.dp,
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(36.dp)
            )
            .background(
                color = LocalScribbleDashColorsPalette.current.surfaceHigh,
                shape = RoundedCornerShape(36.dp)
            )
            .padding(all = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .border(
                    width = 1.dp,
                    color = linesColor,
                    shape = RoundedCornerShape(36.dp)
                )
                .background(
                    color = LocalScribbleDashColorsPalette.current.surfaceHigh,
                    shape = RoundedCornerShape(36.dp)
                )
        ) {
            Canvas(
                modifier = modifier
                    .matchParentSize()
                    .pointerInput(true) {
                        detectDragGestures(
                            onDragStart = {
                                onAction(DrawingAction.OnNewPathStart)
                            },
                            onDragEnd = {
                                onAction(DrawingAction.OnPathEnd)
                            },
                            onDrag = { change, _ ->
                                onAction(DrawingAction.OnDraw(change.position))
                            },
                            onDragCancel = {
                                onAction(DrawingAction.OnPathEnd)
                            }
                        )
                    }
            ) {
                drawGridLines(linesColor, 1.dp)

                paths.fastForEach { pathData ->
                    drawPath(
                        path = pathData.path,
                        color = pathData.color,
                    )
                }
                currentPath?.let {
                    drawPath(
                        path = it.path,
                        color = it.color,
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawGridLines(lineColor: Color, lineWidth: Dp) {
    val canvasWidth = size.width
    val canvasHeight = size.height

    val cellWidth = canvasWidth / 3
    val cellHeight = canvasHeight / 3

    for (i in 1..2) {
        val x = cellWidth * i
        drawLine(
            color = lineColor,
            start = Offset(x, 0f),
            end = Offset(x, canvasHeight),
            strokeWidth = lineWidth.toPx()
        )
    }

    for (i in 1..2) {
        val y = cellHeight * i
        drawLine(
            color = lineColor,
            start = Offset(0f, y),
            end = Offset(canvasWidth, y),
            strokeWidth = lineWidth.toPx()
        )
    }
}

private fun DrawScope.drawPath(
    path: List<Offset>,
    color: Color,
    thickness: Float = 10f,
) {
    val smoothedPath = Path().apply {
        if (path.isNotEmpty()) {
            moveTo(
                x = path.first().x,
                y = path.first().y
            )

            val smoothness = 5
            for (i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if (dx >= smoothness || dy >= smoothness) {
                    quadraticTo(
                        x1 = (from.x + to.x) / 2f,
                        y1 = (from.y + to.y) / 2f,
                        x2 = to.x,
                        y2 = to.y,
                    )
                }
            }
        }
    }
    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = thickness,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round,
        )
    )
}