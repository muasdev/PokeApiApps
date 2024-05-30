package com.muasdev.pokeapiapps.domain.entities.pokeapi

import com.google.gson.annotations.SerializedName

data class MovesEntity(
    @SerializedName("move")
    val move: MoveEntity? = MoveEntity(),
)