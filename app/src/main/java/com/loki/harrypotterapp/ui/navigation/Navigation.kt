package com.loki.harrypotterapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loki.harrypotterapp.ui.HarryPotterAppState
import com.loki.harrypotterapp.ui.character_detail.CharacterDetailScreen
import com.loki.harrypotterapp.ui.character_list.CharacterListScreen
import com.loki.harrypotterapp.ui.search.SearchScreen
import com.loki.harrypotterapp.util.Constants.CHAR_ID
import com.loki.harrypotterapp.util.Constants.CHAR_NAME

@Composable
fun Navigation(
    appState: HarryPotterAppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.CharacterListScreen.route
    ) {

        composable(route = Screens.CharacterListScreen.route) {
            CharacterListScreen(
                openScreen = { route ->
                    appState.navigate(route = route)
                }
            )
        }

        composable(
            route = Screens.DetailScreen.navWithArgs(),
            arguments = listOf(
                navArgument(CHAR_ID) {
                    type = NavType.StringType
                },
                navArgument(CHAR_NAME) {
                    type = NavType.StringType
                }
            )
        )  {

            it.arguments?.getString(CHAR_NAME)?.let { name ->
                CharacterDetailScreen(
                    popUpScreen = { appState.popUp() },
                    name = name
                )
            }
        }

        composable(route = Screens.SearchScreen.route) {

            SearchScreen(
                popUpScreen = {
                    appState.popUp()
                },
                openScreen = { route ->
                    appState.navigate(route)
                }
            )
        }
    }
}


sealed class Screens(val route: String) {

    object CharacterListScreen: Screens("character_list_screen")
    object DetailScreen: Screens("details_screen")
    object SearchScreen: Screens("search_screen")

    fun navWithArgs(): String {
        return "${DetailScreen.route}/{$CHAR_ID}/{$CHAR_NAME}"
    }

    fun navWithCharacterId(id: String, name: String): String {
        return "${DetailScreen.route}/$id/$name"
    }
}