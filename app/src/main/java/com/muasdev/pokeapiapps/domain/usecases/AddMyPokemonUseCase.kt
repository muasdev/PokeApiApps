package com.muasdev.pokeapiapps.domain.usecases

import com.muasdev.pokeapiapps.domain.Repository
import com.muasdev.pokeapiapps.domain.requests.PokemonRequest
import javax.inject.Inject

class AddMyPokemonUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        pokemonRequest: PokemonRequest
    ) = repository.addMyPokemon(pokemonRequest)
}