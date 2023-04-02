package com.loki.harrypotterapp.domain.repository

import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {

    fun getCharacterList(): Flow<Resource<List<CharacterItem>>>
}