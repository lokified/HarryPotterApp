package com.loki.harrypotterapp.ui.character_list

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
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterListRepository
): ViewModel() {

    var characterList = mutableStateOf<List<CharacterItem>>(listOf())
    var message = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var searchedList = listOf<CharacterItem>()
    private var isSearchEmpty = true
    private var isSearching = mutableStateOf(false)

    init {
        getCharacters()
    }

    fun getCharacters() {

        repository.getCharacterList().onEach { result ->

            when(result) {

                is Resource.Loading -> {
                    isLoading.value = true
                }

                is Resource.Error -> {
                    isLoading.value = false
                    message.value  = result.message ?: "Something went wrong"
                }

                is Resource.Success -> {

                    message.value = ""
                    isLoading.value = false
                    characterList.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCharacterList(query: String) {

        val listToSearch = if (isSearchEmpty) characterList.value else searchedList

        viewModelScope.launch(Dispatchers.Main) {

            if (query.isEmpty()) {
                characterList.value = searchedList
                isSearching.value = false
                isSearchEmpty = true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchEmpty) {
                searchedList = characterList.value
                isSearchEmpty = false
            }

            characterList.value = results
            isSearching.value = true
        }
    }
}