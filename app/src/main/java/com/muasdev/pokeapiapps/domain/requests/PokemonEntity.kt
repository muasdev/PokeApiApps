package com.muasdev.pokeapiapps.domain.requests

import com.google.gson.annotations.SerializedName

data class PokemonRequest(
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nick_name")
    val nickName: String? = null,
)