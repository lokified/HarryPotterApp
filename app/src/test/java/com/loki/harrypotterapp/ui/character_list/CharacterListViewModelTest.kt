package com.loki.harrypotterapp.ui.character_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.FakeCharacterListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class CharacterListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: CharacterListViewModel
    private val repository = FakeCharacterListRepository()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = CharacterListViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `call get character list`() = runTest {

        viewModel.getCharacters()
        advanceUntilIdle()
        assert(viewModel.characterListState.value.characterList.isNotEmpty())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}