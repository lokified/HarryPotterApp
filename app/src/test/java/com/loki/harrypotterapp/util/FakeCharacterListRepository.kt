package com.loki.harrypotterapp.util

import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class FakeCharacterListRepository: CharacterListRepository {

    override fun getCharacterList() = flow <Resource<List<CharacterItem>>> {
        try {
            emit(Resource.Loading(data = null))
            emit(Resource.Success(data = listOf(model, model2)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }
}