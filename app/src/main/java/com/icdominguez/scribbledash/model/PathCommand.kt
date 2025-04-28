package com.icdominguez.scribbledash.model

import androidx.compose.ui.geometry.Offset

sealed class PathCommand {
    data class MoveTo(val point: Offset) : PathCommand()
    data class LineTo(val point: Offset) : PathCommand()
    data class QuadTo(val control: Offset, val end: Offset) : PathCommand()
    object Close : PathCommand()
}