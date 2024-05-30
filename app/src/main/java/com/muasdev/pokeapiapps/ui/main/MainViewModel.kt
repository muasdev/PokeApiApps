package com.muasdev.pokeapiapps.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muasdev.pokeapiapps.domain.usecases.GetPokemonsUseCase
import com.muasdev.pokeapiapps.utils.InvalidException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    private fun getPokemonFromServer() {
        viewModelScope.launch {
            try {
                _state.value = MainState(
                    pokemons = getPokemonsUseCase(),
                )
            } catch (exception: InvalidException) {
                _state.value = MainState(
                    errorMessage = "${exception.message}",
                )
            }
        }
    }

    init {
        getPokemonFromServer()
    }
}