package com.loki.harrypotterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loki.harrypotterapp.ui.HarryPotterAppState
import com.loki.harrypotterapp.ui.character_list.CharacterListViewModel
import com.loki.harrypotterapp.ui.navigation.Navigation
import com.loki.harrypotterapp.ui.theme.HarryPotterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.characterListState.value.isLoading
            }
        }

        setContent {
            HarryPotterAppTheme {

                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    val appState = rememberAppState()

                    Navigation(appState = appState)
                }
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