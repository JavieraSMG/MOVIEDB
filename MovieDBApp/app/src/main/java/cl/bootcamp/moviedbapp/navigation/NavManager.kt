package cl.bootcamp.moviedbapp.navigation

import DetailsView
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.moviedbapp.view.HomeView
import cl.bootcamp.moviedbapp.viewModel.MoviesViewModel


@Composable
fun NavManager(
    viewModel: MoviesViewModel= hiltViewModel()
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(viewModel, navController)
        }
        composable("DetailsView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            DetailsView(viewModel, navController, id)
        }
    }
}

