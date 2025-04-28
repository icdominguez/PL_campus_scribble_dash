package com.icdominguez.scribbledash

import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.icdominguez.scribbledash.navigation.Screen
import com.icdominguez.scribbledash.navigation.ScribbleDashBottomNavigationBar
import com.icdominguez.scribbledash.ui.designsystem.theme.ScribbleDashTheme
import com.icdominguez.scribbledash.ui.screens.drawing.DrawingScreen
import com.icdominguez.scribbledash.ui.screens.statistics.Statistics
import com.icdominguez.scribbledash.ui.screens.home.HomeScreen
import com.icdominguez.scribbledash.ui.screens.home.HomeScreenViewModel
import com.icdominguez.scribbledash.ui.screens.difficulty.SelectDifficultyScreen
import com.icdominguez.scribbledash.ui.screens.drawing.DrawingViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val window = this.window
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )

        setContent {
            ScribbleDashTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route

                val showBottomBar = currentDestination in listOf(
                    Screen.Home.route,
                    Screen.Statistics.route,
                )

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            ScribbleDashBottomNavigationBar(navController = navController)
                        }
                    },
                ) { innerPadding ->
                    val graph = navController.createGraph(startDestination = Screen.Home.route) {
                        val viewmodel: HomeScreenViewModel = koinViewModel()
                        composable(route = Screen.Home.route) {
                            HomeScreen(
                                state = viewmodel.state.collectAsStateWithLifecycle().value,
                                uiEvent = viewmodel::uiEvent,
                                navigateToSelectDifficultyScreen = {
                                    navController.navigate(Screen.SelectDifficulty.route)
                                }
                            )
                        }
                        composable(route = Screen.Statistics.route) {
                            Statistics()
                        }
                        composable(route = Screen.SelectDifficulty.route) {
                            SelectDifficultyScreen(
                                navigateBack = {
                                    navController.popBackStack()
                                },
                                navigateToDrawScreen = {
                                    navController.navigate(Screen.Drawing.route)
                                }
                            )
                        }
                        composable(route = Screen.Drawing.route) {
                            val viewModel: DrawingViewModel = koinViewModel()
                            DrawingScreen(
                                state = viewModel.state.collectAsStateWithLifecycle().value,
                                uiEvent = viewModel::onAction,
                                navigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                    NavHost(
                        navController = navController,
                        graph = graph,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}