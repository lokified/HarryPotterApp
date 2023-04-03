package com.loki.harrypotterapp.repository

import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock

class CharacterListRepositoryTest {

    companion object {
        fun mockGetCharacterList(
            flowReturn: Flow<Resource<List<CharacterItem>>>
        ) = object : CharacterListRepository {
            override fun getCharacterList(): Flow<Resource<List<CharacterItem>>> = flowReturn
        }
    }

    @Test
    fun `GetCharactersList starts with loading returns Resource Loading`() = runBlocking {
        val characters = mock<List<CharacterItem>>()

        val characterListRepository = mockGetCharacterList(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(characters))
            }
        )

        val result = characterListRepository.getCharacterList().first()

        assert(result is Resource.Loading)
    }

    @Test
    fun `GetCharacterList success returns Resource Data`() = runBlocking {
        val characters = mock<List<CharacterItem>>()

        val characterListRepository = mockGetCharacterList(
            flow {
                emit(Resource.Loading())
                emit(Resource.Success(characters))
            }
        )

        val result = characterListRepository.getCharacterList().last()

        assert(result is Resource.Success && (result.data ?: false) != emptyList<CharacterItem>())
    }

    @Test
    fun `GetCharacterList error returns Resource Error`() = runBlocking {
        val characters = mock<List<CharacterItem>>()

        val characterListRepository = mockGetCharacterList(
            flow {
                emit(Resource.Error(message = "An unexpected error occurred", data = characters))
            }
        )

        val result = characterListRepository.getCharacterList().last()

        assert(result is Resource.Error && result.message == "An unexpected error occurred")
    }
}