package com.muasdev.pokeapiapps.ui.detail

import com.muasdev.pokeapiapps.domain.requests.PokemonRequest

sealed class DetailEvent {

    data object CatchPokemon: DetailEvent()
    data object ResetData: DetailEvent()
    data class AddToMyPokemon(
        val pokemonRequest: PokemonRequest
    ): DetailEvent()
}