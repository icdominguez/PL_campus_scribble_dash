package com.icdominguez.scribbledash.model

import com.icdominguez.scribbledash.R

enum class Difficulty(
    val stringId: Int,
    val icon: Int
) {
    BEGINNER(R.string.difficulty_beginner, R.drawable.beginner_icon),
    CHALLENGING(R.string.difficulty_challenging, R.drawable.challenging_icon),
    MASTER(R.string.difficulty_master, R.drawable.master_icon)
}