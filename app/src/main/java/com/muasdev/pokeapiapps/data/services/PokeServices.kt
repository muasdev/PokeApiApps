package com.muasdev.pokeapiapps.data.services

import com.muasdev.pokeapiapps.data.contants.AppConstants
import com.muasdev.pokeapiapps.domain.entities.ownapi.PokemonEntity
import com.muasdev.pokeapiapps.domain.entities.ownapi.StatusEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.DetailPokemonEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.ResponseEntity
import com.muasdev.pokeapiapps.domain.requests.PokemonRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.muasdev.pokeapiapps.domain.entities.ownapi.ResponseEntity as ResponseOwnApi

interface PokeServices {
    /*poke api*/
    @GET(AppConstants.GET_POKEMONS_ENDPOINT)
    suspend fun getPokemons(): ResponseEntity

    @GET("${AppConstants.GET_DETAIL_POKEMON_ENDPOINT}/{name}")
    suspend fun getDetailPokemon(
        @Path("name") name: String
    ): DetailPokemonEntity

    /*own api*/
    @GET(AppConstants.POKEMON_ENDPOINT)
    suspend fun getMyPokemons(): ResponseOwnApi<List<PokemonEntity>>

    @POST(AppConstants.POKEMON_ENDPOINT)
    suspend fun addMyPokemons(
        @Body pokemonRequest: PokemonRequest
    ): ResponseOwnApi<PokemonEntity>

    @GET(AppConstants.CATCH_POKEMON_ENDPOINT)
    suspend fun catchPokemon(): ResponseOwnApi<StatusEntity>

    @PUT("${AppConstants.POKEMON_ENDPOINT}/{id}")
    suspend fun renamePokemon(
        @Path("id") idPokemon:Int
    ): ResponseOwnApi<List<PokemonEntity>>

    @DELETE("${AppConstants.POKEMON_ENDPOINT}/{id}")
    suspend fun releasePokemon(
        @Path("id") idPokemon:Int
    ): ResponseOwnApi<List<PokemonEntity>>

}