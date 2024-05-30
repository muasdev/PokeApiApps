package com.muasdev.pokeapiapps.domain.entities.ownapi

import com.google.gson.annotations.SerializedName

data class ResponseEntity<T>(
    @SerializedName("message") val message:String? = null,
    @SerializedName("data") val data:T?,
)
