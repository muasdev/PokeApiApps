package com.muasdev.pokeapiapps.ui.mypokemon

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun MyPokemonScreen(
    viewModel: MyPokemonViewModel = viewModel()
) {
    val data by viewModel.state.collectAsState()
    val pokemons = data.pokemons?.data

    val context = LocalContext.current

    val errorMessage = data.errorMessage
    errorMessage?.let {
        Toast.makeText(context, "$errorMessage", Toast.LENGTH_SHORT).show()
        viewModel.onEvent(MyPokemonEvent.ResetData)
    }

    val message = data.message
    message?.let {
        Toast.makeText(context, "$message", Toast.LENGTH_SHORT).show()
        viewModel.onEvent(MyPokemonEvent.ResetData)
    }

    LazyColumn(
        modifier = Modifier.height(24.dp)
    ) {
        items(pokemons?.size ?: 0) { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 0.dp,
                        bottom = 0.dp,
                    )
                    .clickable {
                        pokemons
                            ?.get(item)
                            ?.let { }
                    }
            ) {
                AsyncImage(
                    modifier = Modifier.size(width = 150.dp, height = 180.dp),
                    model = "${pokemons?.get(item)?.image}",
                    contentDescription = "pokemon image"
                )
                Column {
                    Text("${pokemons?.get(item)?.name}", fontSize = 16.sp)
                    Text("${pokemons?.get(item)?.latestNickName}", fontSize = 16.sp)
                }
                Column {
                    ElevatedButton(
                        onClick = {
                            viewModel.onEvent(MyPokemonEvent.RenamePokemon(pokemons?.get(item)?.id ?: 0))
                        }) {
                        Text("Rename")
                    }
                    ElevatedButton(
                        onClick = {
                            viewModel.onEvent(MyPokemonEvent.ReleasePokemon(pokemons?.get(item)?.id ?: 0))
                        }) {
                        Text("Release")
                    }
                }
            }
        }
    }
}