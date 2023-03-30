package com.loki.harrypotterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loki.harrypotterapp.ui.HarryPotterAppState
import com.loki.harrypotterapp.ui.navigation.Navigation
import com.loki.harrypotterapp.ui.theme.HarryPotterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterAppTheme {

                val appState = rememberAppState()

                Navigation(appState = appState)
            }
        }
    }
}


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    HarryPotterAppState(
        navController = navController
    )
}