package com.muasdev.pokeapiapps.domain

import com.muasdev.pokeapiapps.domain.entities.ownapi.PokemonEntity
import com.muasdev.pokeapiapps.domain.entities.ownapi.StatusEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.DetailPokemonEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.ResponseEntity
import com.muasdev.pokeapiapps.domain.requests.PokemonRequest
import com.muasdev.pokeapiapps.domain.entities.ownapi.ResponseEntity as ResponseOwnApi

interface Repository {
    suspend fun getPokemons(): ResponseEntity
    suspend fun getDetailPokemon(name: String): DetailPokemonEntity
    suspend fun getMyPokemons(): ResponseOwnApi<List<PokemonEntity>>
    suspend fun addMyPokemon(
        pokemonRequest: PokemonRequest
    ): ResponseOwnApi<PokemonEntity>
    suspend fun catchPokemon(): ResponseOwnApi<StatusEntity>
    suspend fun renamePokemon(
        idPokemon: Int
    ): ResponseOwnApi<List<PokemonEntity>>
    suspend fun releasePokemon(
        idPokemon: Int
    ): ResponseOwnApi<List<PokemonEntity>>
}