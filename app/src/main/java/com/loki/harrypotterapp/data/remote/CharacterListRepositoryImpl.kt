package com.loki.harrypotterapp.data.remote

import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val api: HarryPotterApi
): CharacterListRepository {

    override fun getCharacterList() = flow<Resource<List<CharacterItem>>> {
        try {
            emit(Resource.Loading(data = null))
            emit(Resource.Success(data = api.getCharacterList()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = null))
        } catch (e: IOException) {
            emit(Resource.Error("check your internet connection", data = null))
        }
    }
}