package com.example.easy_dealership.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easy_dealership.screens.CadastroScreen
import com.example.easy_dealership.screens.DetalhesScreen
import com.example.easy_dealership.screens.HomeScreen
import com.example.easy_dealership.screens.PatioScreen
import com.example.easy_dealership.screens.VeiculosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("veiculos") { VeiculosScreen(navController) }
        composable("patio") { PatioScreen(navController) }
        composable("cadastro") { CadastroScreen(navController) }
        composable("detalhes/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetalhesScreen(navController, id)
        }
    }
}
