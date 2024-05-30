package com.muasdev.pokeapiapps.data

import com.muasdev.pokeapiapps.data.services.PokeServices
import com.muasdev.pokeapiapps.di.OwnApi
import com.muasdev.pokeapiapps.di.PokeApi
import com.muasdev.pokeapiapps.domain.Repository
import com.muasdev.pokeapiapps.domain.entities.ownapi.PokemonEntity
import com.muasdev.pokeapiapps.domain.entities.ownapi.StatusEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.DetailPokemonEntity
import com.muasdev.pokeapiapps.domain.entities.pokeapi.ResponseEntity
import com.muasdev.pokeapiapps.domain.requests.PokemonRequest
import javax.inject.Inject
import com.muasdev.pokeapiapps.domain.entities.ownapi.ResponseEntity as ResponseOwnApi

class RepoImpl @Inject constructor(
    @PokeApi private val pokeApiServices: PokeServices,
    @OwnApi private val ownApiServices: PokeServices,
)
    : Repository {
    override suspend fun getPokemons(): ResponseEntity {
        return pokeApiServices.getPokemons()
    }

    override suspend fun getDetailPokemon(name: String): DetailPokemonEntity {
        return pokeApiServices.getDetailPokemon(name)
    }

    override suspend fun getMyPokemons(): ResponseOwnApi<List<PokemonEntity>> {
        return ownApiServices.getMyPokemons()
    }

    override suspend fun addMyPokemon(pokemonRequest: PokemonRequest): ResponseOwnApi<PokemonEntity> {
        return ownApiServices.addMyPokemons(pokemonRequest)
    }

    override suspend fun catchPokemon(): ResponseOwnApi<StatusEntity> {
        return ownApiServices.catchPokemon()
    }

    override suspend fun renamePokemon(idPokemon: Int): ResponseOwnApi<List<PokemonEntity>> {
        return ownApiServices.renamePokemon(idPokemon)
    }

    override suspend fun releasePokemon(idPokemon: Int): ResponseOwnApi<List<PokemonEntity>> {
        return ownApiServices.releasePokemon(idPokemon)
    }
}