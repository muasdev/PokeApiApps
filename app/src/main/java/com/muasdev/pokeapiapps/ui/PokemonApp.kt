package com.muasdev.pokeapiapps.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.muasdev.pokeapiapps.ui.detail.DetailPokemonScreen
import com.muasdev.pokeapiapps.ui.detail.DetailViewModel
import com.muasdev.pokeapiapps.ui.main.MainScreen
import com.muasdev.pokeapiapps.ui.main.MainViewModel
import com.muasdev.pokeapiapps.ui.mypokemon.MyPokemonScreen
import com.muasdev.pokeapiapps.ui.mypokemon.MyPokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonAppBar(
    currentScreen: String?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(getScreenTitleByRoute(currentScreen ?: "")) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "icon back button"
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PokemonAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                scrollBehavior,
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = Screen.HomeScreen.route) {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    onItemClicked = {
                        navController.navigate(Screen.DetailScreen.passPokemonName(it ?: ""))
                    },
                    toMyPokemonPage = {
                        navController.navigate(Screen.MyPokemonScreen.route)
                    },
                    mainViewModel = viewModel
                )
            }
            composable(
                route = Screen.DetailScreen.route,
            ) { backStackEntry ->
                val viewModel = hiltViewModel<DetailViewModel>()
                DetailPokemonScreen(
                    modifier = Modifier.fillMaxHeight(),
                    pokemonName = backStackEntry.arguments?.getString("pokemonName"),
                    detailViewModel = viewModel
                )
            }
            composable(
                route = Screen.MyPokemonScreen.route
            ) {
                val viewModel = hiltViewModel<MyPokemonViewModel>()
                MyPokemonScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("HomeScreen")
    data object MyPokemonScreen : Screen("MyPokemonScreen")

    data object DetailScreen : Screen("DetailsScreen/{pokemonName}") {
        fun passPokemonName(name: String): String {
            return this.route.replace(oldValue = "{pokemonName}", newValue = name)
        }
    }
}

fun getScreenTitleByRoute(route: String): String {
    return when(route) {
        "MyPokemonScreen" -> "My Pokemon"
        "DetailsScreen/{pokemonName}" -> "Detail"
        else -> {
            "Pokemon"
        }
    }
}