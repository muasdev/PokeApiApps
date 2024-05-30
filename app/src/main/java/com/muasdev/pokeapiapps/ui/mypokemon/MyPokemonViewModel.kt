package com.muasdev.pokeapiapps.ui.mypokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muasdev.pokeapiapps.domain.usecases.GetMyPokemonsUseCase
import com.muasdev.pokeapiapps.domain.usecases.ReleasePokemonUseCase
import com.muasdev.pokeapiapps.domain.usecases.RenamePokemonUseCase
import com.muasdev.pokeapiapps.utils.InvalidException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MyPokemonViewModel @Inject constructor(
    private val getMyPokemonsUseCase: GetMyPokemonsUseCase,
    private val renamePokemonUseCase: RenamePokemonUseCase,
    private val releasePokemonUseCase: ReleasePokemonUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(MyPokemonState())
    val state: StateFlow<MyPokemonState> = _state.asStateFlow()

    fun onEvent(event: MyPokemonEvent) {
        when(event) {
            is MyPokemonEvent.RenamePokemon -> {
                renamePokemon(event.idPokemon)
            }
            is MyPokemonEvent.ReleasePokemon -> {
                releasePokemon(event.idPokemon)
            }
            is MyPokemonEvent.ResetData -> {
                removeErrorMessage()
                removeMessage()
            }
        }
    }

    private fun getMyPokemon() {
        viewModelScope.launch {
            try {
                _state.value = MyPokemonState(
                    pokemons = getMyPokemonsUseCase(),
                    message = null
                )
            } catch (exception: InvalidException) {
                _state.value = MyPokemonState(
                    errorMessage = "${exception.message}",
                )
            }
        }
    }

    private fun renamePokemon(idPokemon: Int) {
        viewModelScope.launch {
            try {
                val data = renamePokemonUseCase(idPokemon = idPokemon)
                val message = data.message
                _state.update {
                    it.copy(
                        pokemons = data,
                        message = message
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

    private fun releasePokemon(idPokemon: Int) {
        viewModelScope.launch {
            try {
                val data = releasePokemonUseCase(idPokemon = idPokemon)
                val message = data.message
                _state.update {
                    it.copy(
                        pokemons = data,
                        message = message
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

    private fun removeErrorMessage() {
        _state.update {
            it.copy(
                errorMessage = null,
            )
        }
    }

    private fun removeMessage() {
        _state.update {
            it.copy(
                message = null,
            )
        }
    }

    init {
        getMyPokemon()
    }
}