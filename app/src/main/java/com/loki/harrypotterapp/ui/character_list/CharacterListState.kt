package com.loki.harrypotterapp.ui.character_list

import com.loki.harrypotterapp.domain.models.CharacterItem

data class CharacterListState(
    val isLoading: Boolean = false,
    val message: String = "",
    val characterList: List<CharacterItem> = emptyList()
)
