package com.muasdev.pokeapiapps.domain.entities.ownapi

import com.google.gson.annotations.SerializedName

data class PokemonEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nick_name")
    val nickName: String? = null,
    @SerializedName("latest_nick_name")
    val latestNickName: String? = null,
)
