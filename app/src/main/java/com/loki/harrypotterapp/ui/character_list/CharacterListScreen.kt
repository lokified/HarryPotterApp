package com.loki.harrypotterapp.ui.character_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loki.harrypotterapp.ui.character_list.components.CharacterItem
import com.loki.harrypotterapp.ui.navigation.Screens

@Composable
fun CharacterListScreen(
    openScreen: (String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {

    Column {

        HomeTopBar(
            title = "Harry Potter",
            onSearch = { viewModel.searchCharacterList(it) },
            onClose = { viewModel.getCharacters() }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (viewModel.isLoading.value) {
                CircularProgressIndicator()
            }

            if (viewModel.message.value.isNotEmpty()) {
                Text(text = viewModel.message.value)
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(viewModel.characterList.value) { character ->

                    CharacterItem(
                        characterItem = character,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onItemClick = {
                            openScreen(Screens.DetailScreen.navWithCharacterId(it.id, it.name))
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun HomeTopBar(
    title: String,
    onSearch: (String) -> Unit,
    onClose: () -> Unit
) {

    var isVisible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    TopAppBar(
        title = {

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(initialOffsetY = { it/1 }),
                exit = slideOutVertically(targetOffsetY = { it/15 })
            ) {
                SearchField(
                    onSearch = {
                        onSearch(it)
                        value = it
                    }
                )
            }

            Text(text = title, fontSize = 18.sp)

        },
        actions = {
            IconButton(
                onClick = {
                    isVisible  = !isVisible

                    if (!isVisible && value.isNotEmpty()) {
                        onClose()
                    }
                }
            ) {
                Icon(
                    imageVector = if (!isVisible) Icons.Rounded.Search else Icons.Rounded.Cancel,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun SearchField(
    onSearch: (String) -> Unit = {}
) {

    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        placeholder = {
            Text(text = "Enter Name")
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent
        ),
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}