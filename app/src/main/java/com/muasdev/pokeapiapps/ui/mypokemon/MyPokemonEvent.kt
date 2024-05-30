package com.muasdev.pokeapiapps.ui.mypokemon

sealed class MyPokemonEvent {

    data class RenamePokemon(
        val idPokemon: Int
    ): MyPokemonEvent()
    data class ReleasePokemon(
        val idPokemon: Int
    ): MyPokemonEvent()
    data object ResetData: MyPokemonEvent()
}