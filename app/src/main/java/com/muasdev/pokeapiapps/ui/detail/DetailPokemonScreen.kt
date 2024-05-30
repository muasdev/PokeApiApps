package com.muasdev.pokeapiapps.ui.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.muasdev.pokeapiapps.domain.requests.PokemonRequest

@Composable
fun DetailPokemonScreen(
    modifier: Modifier,
    pokemonName: String?,
    detailViewModel: DetailViewModel = viewModel()
) {
    val data by detailViewModel.state.collectAsState()
    val pokemon = data.detailPokemon

    val pokemonTypes = mutableListOf<String>()
    pokemon?.types?.forEach {
        pokemonTypes.add(it.type?.name ?: "")
    }

    val pokemonMoves = mutableListOf<String>()
    pokemon?.moves?.forEach {
        pokemonMoves.add(it.move?.name ?: "")
    }

    val catchStatus = data.catchStatus
    val pokemonAddedStatus = data.pokemonAdded?.message

    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        val context = LocalContext.current
        val openAlertDialog = remember { mutableStateOf(false) }
        pokemon?.sprites?.frontDefault?.let { image ->
            AsyncImage(
                modifier = Modifier
                    .size(width = 250.dp, height = 250.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                model = image,
                contentDescription = "pokemon image"
            )
        }
        Text(text = "$pokemonName", color = Color.Black, fontSize = 25.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Types", color = Color.Black, fontSize = 22.sp)
        Divider(Modifier.width(200.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = pokemonTypes.joinToString { it })
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Moves", color = Color.Black, fontSize = 22.sp)
        Divider(Modifier.width(200.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = pokemonMoves.joinToString { it })
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                detailViewModel.onEvent(DetailEvent.CatchPokemon)
            }) {
            Text("Catch")
        }

        catchStatus?.let {
            if (it) {
                openAlertDialog.value = true
            } else {
                Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show()
            }
        }

        pokemonAddedStatus?.let {
            Toast.makeText(context, "Pokemon saved", Toast.LENGTH_SHORT).show()
            detailViewModel.onEvent(DetailEvent.ResetData)
        }

        if (openAlertDialog.value) {
            var nickNameField by remember { mutableStateOf("") }
            AlertDialog(
                onDismissRequest = {
                    openAlertDialog.value = false
                    detailViewModel.onEvent(DetailEvent.ResetData)
                },
                confirmButton = {
                    ElevatedButton(
                        onClick = {
                            openAlertDialog.value = false
                            detailViewModel.onEvent(DetailEvent.ResetData)
                            detailViewModel.onEvent(DetailEvent.AddToMyPokemon(
                                pokemonRequest = PokemonRequest(
                                    image = pokemon?.sprites?.frontDefault ?: "",
                                    name = pokemonName,
                                    nickName = nickNameField.ifEmpty { pokemonName },
                                )
                            ))
                        }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    ElevatedButton(
                        onClick = {
                            openAlertDialog.value = false
                            detailViewModel.onEvent(DetailEvent.ResetData)
                        }) {
                        Text("Cancel")
                    }
                },
                title = { Text(text = "Successfully catch pokemon") },
                text = {
                    TextField(
                        value = nickNameField,
                        onValueChange = { nickNameField = it },
                        placeholder = { Text("Enter Nickname") },
                    )
                },
            )
        }
    }
}