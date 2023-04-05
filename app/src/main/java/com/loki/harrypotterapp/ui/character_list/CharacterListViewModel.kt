package com.loki.harrypotterapp.ui.character_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterListRepository
): ViewModel() {

    private val _characterListState = MutableStateFlow(CharacterListState())
    val characterListState = _characterListState.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() {

        repository.getCharacterList().onEach { result ->

            when(result) {

                is Resource.Loading -> {
                    _characterListState.value = CharacterListState(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _characterListState.value = CharacterListState(
                        message = result.message ?: "Something went wrong"
                    )
                }

                is Resource.Success -> {
                    _characterListState.value = CharacterListState(
                        characterList = result.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}