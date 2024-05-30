package com.muasdev.pokeapiapps.domain.usecases

import com.muasdev.pokeapiapps.domain.Repository
import javax.inject.Inject

class GetMyPokemonsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getMyPokemons()
}