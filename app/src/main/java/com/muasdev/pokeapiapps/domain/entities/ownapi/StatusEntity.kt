package com.muasdev.pokeapiapps.domain.entities.ownapi

import com.google.gson.annotations.SerializedName

data class StatusEntity(
    @SerializedName("status") val status:Boolean=false
)
