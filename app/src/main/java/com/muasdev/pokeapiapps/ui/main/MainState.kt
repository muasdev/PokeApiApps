package com.muasdev.pokeapiapps.ui.main

import com.muasdev.pokeapiapps.domain.entities.pokeapi.ResponseEntity

data class MainState (
    val pokemons: ResponseEntity? = null,
    val errorMessage: String? = null,
)