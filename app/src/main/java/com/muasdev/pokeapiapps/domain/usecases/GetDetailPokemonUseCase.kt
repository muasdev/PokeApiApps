package com.muasdev.pokeapiapps.domain.usecases

import com.muasdev.pokeapiapps.domain.Repository
import javax.inject.Inject

class GetDetailPokemonUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(name: String) = repository.getDetailPokemon(name)
}