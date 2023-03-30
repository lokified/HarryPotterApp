package com.loki.harrypotterapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.loki.harrypotterapp.ui.HarryPotterAppState

@Composable
fun Navigation(
    appState: HarryPotterAppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.HomeListScreen.route
    ) {

    }
}


sealed class Screens(val route: String) {

    object HomeListScreen: Screens("home_list_screen")
    object DetailScreen: Screens("details_screen")
}