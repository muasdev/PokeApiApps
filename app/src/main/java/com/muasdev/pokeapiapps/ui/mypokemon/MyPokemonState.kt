package com.muasdev.pokeapiapps.ui.mypokemon

import com.muasdev.pokeapiapps.domain.entities.ownapi.PokemonEntity
import com.muasdev.pokeapiapps.domain.entities.ownapi.ResponseEntity

data class MyPokemonState (
    val pokemons: ResponseEntity<List<PokemonEntity>>? = null,
    val errorMessage: String? = null,
    val message: String? = null,
)