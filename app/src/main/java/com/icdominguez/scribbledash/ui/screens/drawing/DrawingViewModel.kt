package com.icdominguez.scribbledash.ui.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private val MAX_BUFFER_SIZE = 5

data class DrawingState(
    val selectedColor: Color = Color.Black,
    val currentPath: PathData? = null,
    val paths: List<PathData> = emptyList(),
    val undoPaths: List<PathData> = emptyList(),
    val redoPaths: List<PathData> = emptyList(),
)

data class PathData(
    val id: String,
    val color: Color = Color.White,
    val path: List<Offset>
)

sealed interface DrawingAction {
    data object OnNewPathStart: DrawingAction
    data class OnDraw(val offset: Offset): DrawingAction
    data object OnPathEnd: DrawingAction
    data object OnClearCanvasClick: DrawingAction
    data object OnUndoButtonClick: DrawingAction
    data object OnRedoButtonClick: DrawingAction
}

class DrawingViewModel : ViewModel() {
    private val _state = MutableStateFlow(DrawingState())
    val state = _state.asStateFlow()

    fun onAction(action: DrawingAction) {
        when (action) {
            DrawingAction.OnClearCanvasClick -> clearCanvas()
            is DrawingAction.OnDraw -> onDraw(action.offset)
            DrawingAction.OnNewPathStart -> onNewPathStart()
            DrawingAction.OnPathEnd -> onPathEnd()
            DrawingAction.OnUndoButtonClick -> onUndoButtonClick()
            DrawingAction.OnRedoButtonClick -> onRedoButtonClick()
        }
    }

    private fun onRedoButtonClick() {
        val currentPath = state.value.redoPaths.last()

        _state.update {
            it.copy(
                paths = it.paths + currentPath,
                redoPaths = it.redoPaths - currentPath,
                undoPaths = it.undoPaths + currentPath,
            )
        }
    }

    private fun onUndoButtonClick() {
        val currentPath = state.value.paths.last()

        _state.update {
            it.copy(
                paths = it.paths - currentPath,
                undoPaths = it.undoPaths - currentPath,
                redoPaths = it.redoPaths + currentPath
            )
        }
    }

    private fun onPathEnd() {
        val currentPathData = state.value.currentPath ?: return

        val newUndoPaths = state.value.undoPaths.toMutableList().apply {
            if(size == MAX_BUFFER_SIZE) {
                removeAt(0)
                add(currentPathData)
            } else {
                add(currentPathData)
            }
        }

        _state.update {
            it.copy(
                currentPath = null,
                paths = it.paths + currentPathData,
                undoPaths = newUndoPaths,
                redoPaths = emptyList(),
            )
        }
    }

    private fun onNewPathStart() {
        _state.update { it.copy(
            currentPath = PathData(
                id = System.currentTimeMillis().toString(),
                color = it.selectedColor,
                path = emptyList()
            )
        ) }
    }

    private fun onDraw(offset: Offset) {
        val currentPathData = state.value.currentPath ?: return
        _state.update {
            it.copy(
                currentPath = currentPathData.copy(
                    path = currentPathData.path + offset
                )
            )
        }
    }

    private fun clearCanvas() {
        _state.update { it.copy(
                currentPath = null,
                paths = emptyList(),
                undoPaths = emptyList(),
                redoPaths = emptyList()
            )
        }
    }
}