package com.muasdev.pokeapiapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.muasdev.pokeapiapps.ui.PokemonApp
import com.muasdev.pokeapiapps.ui.theme.PokeApiAppsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeApiAppsTheme {
                PokemonApp()
            }
        }
    }
}

