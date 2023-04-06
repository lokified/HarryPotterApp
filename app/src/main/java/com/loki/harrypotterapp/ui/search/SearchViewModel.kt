package com.loki.harrypotterapp.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: CharacterListRepository
): ViewModel() {

    var characterList = mutableStateOf<List<CharacterItem>>(listOf())
    var message = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    var searchedList = mutableStateOf<List<CharacterItem>>(listOf())
    private var isSearchEmpty = true

    init {
        getCharacters()
    }

    fun getCharacters() {

        repository.getCharacterList().onEach { result ->

            when(result) {

                is Resource.Loading -> {}

                is Resource.Error -> {
                    errorMessage.value  = result.message ?: "Something went wrong"
                }

                is Resource.Success -> {
                    errorMessage.value = ""
                    message.value = "No Search Results"
                    characterList.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCharacterList(query: String) {
        val listToSearch = if (isSearchEmpty) characterList.value else searchedList.value

        viewModelScope.launch(Dispatchers.Main) {

            if (query.isEmpty()) {
                searchedList.value = emptyList()
                message.value = "No Search Results"
                isSearchEmpty = true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }

            if (errorMessage.value.isEmpty()) {
                searchedList.value = results
                message.value = ""
            }
        }
    }
}