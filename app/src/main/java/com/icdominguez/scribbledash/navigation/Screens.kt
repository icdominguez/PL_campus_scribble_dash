package com.icdominguez.scribbledash.navigation

sealed class Screen(val route: String) {
    data object Home: Screen(route = "Home")
    data object LeftHand: Screen(route = "LeftHand")
    data object SelectDifficulty: Screen(route = "SelectDifficulty")
    data object Drawing: Screen(route = "Drawing")
}