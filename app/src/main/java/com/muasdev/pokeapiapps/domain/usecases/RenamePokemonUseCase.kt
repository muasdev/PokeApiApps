package com.muasdev.pokeapiapps.domain.usecases

import com.muasdev.pokeapiapps.domain.Repository
import javax.inject.Inject

class RenamePokemonUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        idPokemon: Int
    ) = repository.renamePokemon(idPokemon)
}