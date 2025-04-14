package com.icdominguez.scribbledash.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.designsystem.theme.LocalScribbleDashColorsPalette

data class NavigationItem(
    val icon: Int,
    val route: String,
)

@Composable
fun ScribbleDashBottomNavigationBar(
    navController: NavController,
) {
    val selectedNavigationIndex = remember { mutableIntStateOf(1) }

    val navigationItems = listOf(
        NavigationItem(
            icon = R.drawable.chart,
            route = Screen.LeftHand.route,
        ),
        NavigationItem(
            icon = R.drawable.home,
            route = Screen.Home.route,
        ),
    )

    NavigationBar(
        containerColor = LocalScribbleDashColorsPalette.current.surfaceHigh,
    ) {
        navigationItems.mapIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(navigationItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(navigationItem.icon),
                        contentDescription = null,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LocalScribbleDashColorsPalette.current.primary,
                    unselectedIconColor = LocalScribbleDashColorsPalette.current.surfaceLowest,
                    indicatorColor = Color.Transparent,
                )
            )
        }
    }
}