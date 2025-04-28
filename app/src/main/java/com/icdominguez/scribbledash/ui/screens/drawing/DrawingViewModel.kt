package com.icdominguez.scribbledash.ui.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icdominguez.scribbledash.domain.GetRandomAssetUseCase
import com.icdominguez.scribbledash.model.PathCommand
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val MAX_BUFFER_SIZE = 5

data class DrawingState(
    val selectedColor: Color = Color.Black,
    val currentPath: PathData? = null,
    val paths: List<PathData> = emptyList(),
    val undoPaths: List<PathData> = emptyList(),
    val redoPaths: List<PathData> = emptyList(),
    val vectorData: VectorData = VectorData(),
    val countdownFinished: Boolean = false,
    val timeLeft: Int = 3,
)

data class PathData(
    val id: String,
    val color: Color = Color.White,
    val path: List<Offset>
)

data class VectorData(
    var paths: List<Path> = emptyList(),
    var viewportWidth: Float = 0.0f,
    var viewportHeight: Float = 0.0f,
)

sealed interface DrawingAction {
    data object OnNewPathStart: DrawingAction
    data class OnDraw(val offset: Offset): DrawingAction
    data object OnPathEnd: DrawingAction
    data object OnClearCanvasClick: DrawingAction
    data object OnUndoButtonClick: DrawingAction
    data object OnRedoButtonClick: DrawingAction
}

class DrawingViewModel(
    private val getRandomAssetUseCase: GetRandomAssetUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(DrawingState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val vectorData = getRandomAssetUseCase()
            _state.update { it.copy(vectorData = vectorData) }

            startCountdown()
        }
    }

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

    private fun startCountdown() {
        viewModelScope.launch {
            for (i in state.value.timeLeft downTo 1) {
                _state.update { it.copy(timeLeft = i) }
                delay(1000L)
            }
            _state.update {
                it.copy(countdownFinished = true)
            }
        }
    }
}