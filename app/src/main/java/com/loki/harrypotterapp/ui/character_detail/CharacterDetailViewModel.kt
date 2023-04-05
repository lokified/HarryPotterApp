package com.loki.harrypotterapp.ui.character_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.Constants.CHAR_ID
import com.loki.harrypotterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterListRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _characterDetail = MutableStateFlow(CharacterDetailState())
    val characterDetail = _characterDetail.asStateFlow()

    init {
        savedStateHandle.get<String>(CHAR_ID)?.let { id ->
            getCharacterDetail(id)
        }
    }

    fun getCharacterDetail(id: String) {

        repository.getCharacterList().onEach { result ->

            when(result) {
                is Resource.Loading -> {
                    _characterDetail.value = CharacterDetailState(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _characterDetail.value = CharacterDetailState(
                        message = result.message ?: "Something went wrong"
                    )
                }

                is Resource.Success -> {

                    val characterList = result.data

                    val character = characterList?.find { it.id == id }

                    _characterDetail.value = CharacterDetailState(
                        characterDetail = character
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}