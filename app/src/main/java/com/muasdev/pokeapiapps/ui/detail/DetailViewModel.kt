package com.muasdev.pokeapiapps.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muasdev.pokeapiapps.domain.requests.PokemonRequest
import com.muasdev.pokeapiapps.domain.usecases.AddMyPokemonUseCase
import com.muasdev.pokeapiapps.domain.usecases.CatchPokemonUseCase
import com.muasdev.pokeapiapps.domain.usecases.GetDetailPokemonUseCase
import com.muasdev.pokeapiapps.utils.InvalidException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase,
    private val addMyPokemonUseCase: AddMyPokemonUseCase,
): ViewModel() {

    val pokemonName: String = checkNotNull(savedStateHandle["pokemonName"]?:"")

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.CatchPokemon -> {
                catchPokemon()
            }
            is DetailEvent.AddToMyPokemon -> {
                addMyPokemon(event.pokemonRequest)
            }
            is DetailEvent.ResetData -> {
                removeCatchStatus()
                removePokemonAddedData()
                removeErrorMessage()
            }
        }
    }

    private fun getDetailPokemon(name: String) {
        viewModelScope.launch {
            try {
                _state.value = DetailState(
                    detailPokemon = getDetailPokemonUseCase(name),
                )
            } catch (exception: InvalidException) {
                _state.value = DetailState(
                    errorMessage = "${exception.message}",
                )
            }
        }
    }

    private fun catchPokemon() {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        catchStatus = catchPokemonUseCase().data?.status,
                    )
                }
            } catch (exception: InvalidException) {
                _state.update {
                    it.copy(
                        errorMessage = "${exception.message}",
                    )
                }
            }
        }
    }

    private fun removeCatchStatus() {
        _state.update {
            it.copy(
                catchStatus = null,
            )
        }
    }

    private fun removePokemonAddedData() {
        _state.update {
            it.copy(
                pokemonAdded = null,
            )
        }
    }

    private fun removeErrorMessage() {
        _state.update {
            it.copy(
                errorMessage = null,
            )
        }
    }

    private fun addMyPokemon(
        pokemonRequest: PokemonRequest
    ) {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        pokemonAdded = addMyPokemonUseCase(pokemonRequest),
                    )
                }
            } catch (exception: InvalidException) {
                _state.update {
                    it.copy(
                        errorMessage = "${exception.message}",
                    )
                }
            }
        }
    }

    init {
        getDetailPokemon(pokemonName)
    }
}