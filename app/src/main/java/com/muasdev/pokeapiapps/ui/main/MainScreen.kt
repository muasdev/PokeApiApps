package com.muasdev.pokeapiapps.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.muasdev.pokeapiapps.ui.theme.PokeApiAppsTheme
import com.muasdev.pokeapiapps.utils.getSpritesUrl

@Composable
fun MainScreen(
    onItemClicked: (String?) -> Unit,
    toMyPokemonPage: () -> Unit,
    mainViewModel: MainViewModel = viewModel()
) {
    val data by mainViewModel.state.collectAsState()
    val pokemons = data.pokemons?.results
    LazyColumn(
        modifier = Modifier.height(24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    toMyPokemonPage.invoke()
                }) {
                Text("My Pokemon")
            }
        }
        items(pokemons?.size ?: 0) { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        pokemons
                            ?.get(item)
                            ?.let { onItemClicked.invoke(it.name) }
                    }
            ) {
                AsyncImage(
                    modifier = Modifier.size(width = 150.dp, height = 180.dp),
                    model = "${pokemons?.get(item)?.url?.getSpritesUrl()}",
                    contentDescription = "pokemon image"
                )
                Spacer(modifier = Modifier.width(24.dp))
                Column {
                    Text("${pokemons?.get(item)?.name}", fontSize = 25.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonPreview() {
    PokeApiAppsTheme {
        MainScreen(
            onItemClicked = {},
            toMyPokemonPage = {},
        )
    }
}