package com.muasdev.pokeapiapps.domain.usecases

import com.muasdev.pokeapiapps.domain.Repository
import javax.inject.Inject

class ReleasePokemonUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        idPokemon: Int
    ) = repository.releasePokemon(idPokemon)
}