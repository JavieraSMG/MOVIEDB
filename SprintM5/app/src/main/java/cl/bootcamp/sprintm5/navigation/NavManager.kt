package cl.bootcamp.sprintm5.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.sprintm5.model.ProductData
import cl.bootcamp.sprintm5.viewmodel.ProductViewModel
import cl.bootcamp.sprintm5.views.CartView
import cl.bootcamp.sprintm5.views.DetailsView
import cl.bootcamp.sprintm5.views.MainView


@Composable
fun NavManager(viewModel: ProductViewModel) {
    val navController = rememberNavController()

    NavHost( navController = navController, startDestination = "Home"){

        composable("Home") {
            MainView( navController,viewModel)
        }
        composable("Details/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
        )){
            val id = it.arguments?.getInt("id") ?: 0
            DetailsView( navController,viewModel,id)
        }

        composable("Cart/{id}") {
            CartView( navController,viewModel)
        }


    }
}
