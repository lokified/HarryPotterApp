package com.loki.harrypotterapp.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.ui.character_list.CharacterListViewModel
import com.loki.harrypotterapp.util.FakeCharacterListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SearchViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: SearchViewModel
    private val repository = FakeCharacterListRepository()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = SearchViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `call get character list`() = runTest {

        viewModel.getCharacters()
        advanceUntilIdle()
        assert(viewModel.characterList.value.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `call get searched character in a list`() = runTest {

        viewModel.getCharacters()
        val query = "Harry"
        delay(2000L)
        viewModel.searchCharacterList(query)
        advanceUntilIdle()
        assert(viewModel.searchedList.value.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}