package com.loki.harrypotterapp.ui

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
class HarryPotterAppState (
    val navController: NavHostController
) {

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route = route) {
            launchSingleTop = true
        }
    }
}