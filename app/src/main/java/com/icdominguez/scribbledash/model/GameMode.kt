package com.icdominguez.scribbledash.model

import com.icdominguez.scribbledash.R

enum class GameMode(
    val stringId: Int,
    val icon: Int
) {
    ONE_ROUND_WONDER(
        stringId = R.string.one_round_wonder,
        icon = R.drawable.one_round_wonder
    )
}