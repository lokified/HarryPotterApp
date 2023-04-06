package com.loki.harrypotterapp.ui.character_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.loki.harrypotterapp.domain.models.CharacterItem
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.ui.character_list.CharacterListViewModel
import com.loki.harrypotterapp.util.FakeCharacterListRepository
import com.loki.harrypotterapp.util.Resource
import com.loki.harrypotterapp.util.model
import io.mockk.MockKAnnotations
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.notNull
import org.mockito.kotlin.verify

class CharacterDetailViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: CharacterDetailViewModel

    private val repository = FakeCharacterListRepository()

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = CharacterDetailViewModel(repository, savedStateHandle)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `call get character detail`() = runTest  {

        viewModel.getCharacterDetail(model.id)
        advanceUntilIdle()
        assert(viewModel.characterDetail.value.characterDetail?.name?.isNotEmpty()!!)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}