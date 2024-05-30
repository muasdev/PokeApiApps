package com.muasdev.pokeapiapps.domain.entities.pokeapi

import com.google.gson.annotations.SerializedName

data class DetailPokemonEntity(
    @SerializedName("moves")
    val moves: List<MovesEntity>? = listOf(),
    @SerializedName("types")
    val types: List<TypesEntity>? = listOf(),
    @SerializedName("sprites")
    val sprites: SpritesEntity? = null,

)

data class SpritesEntity (
    @SerializedName("front_default")
    val frontDefault: String? = null
)
