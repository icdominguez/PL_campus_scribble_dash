package com.icdominguez.scribbledash.navigation

sealed class Screen(val route: String) {
    data object Home: Screen(route = "Home")
    data object Statistics: Screen(route = "Statistics")
    data object SelectDifficulty: Screen(route = "SelectDifficulty")
    data object Drawing: Screen(route = "Drawing")
}