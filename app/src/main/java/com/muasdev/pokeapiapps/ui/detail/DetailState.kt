package com.muasdev.pokeapiapps.ui.detail

import com.muasdev.pokeapiapps.domain.entities.ownapi.PokemonEntity
import com.muasdev.pokeapiapps.domain.entities.ownapi.ResponseEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.DetailPokemonEntity

data class DetailState (
    val detailPokemon: DetailPokemonEntity? = null,
    val catchStatus: Boolean? = null,
    val pokemonAdded: ResponseEntity<PokemonEntity>? = null,
    val errorMessage: String? = null,
)