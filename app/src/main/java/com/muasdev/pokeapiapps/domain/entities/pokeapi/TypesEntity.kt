package com.muasdev.pokeapiapps.domain.entities.pokeapi

import com.google.gson.annotations.SerializedName

data class TypesEntity(
    @SerializedName("slot")
    val slot: Int? = 0,
    @SerializedName("type")
    val type: TypeEntity? = TypeEntity()
)