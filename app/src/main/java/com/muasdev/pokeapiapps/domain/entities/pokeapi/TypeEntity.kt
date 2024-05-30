package com.muasdev.pokeapiapps.domain.entities.pokeapi

import com.google.gson.annotations.SerializedName

data class TypeEntity(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)