package com.muasdev.pokeapiapps.domain.entities.pokeapi

import com.google.gson.annotations.SerializedName

data class ResponseEntity(
    @SerializedName("results") val results:List<PokemonEntity>?,
)
