package cl.bootcamp.e1m6.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.e1m6.viewModel.ContactsViewModel
import cl.bootcamp.e1m6.views.AddUserView
import cl.bootcamp.e1m6.views.MainView

@Composable
fun NavManager(viewModel: ContactsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainView") {
        composable("mainView") {
            MainView(viewModel, navController)
        }
        composable("AddUserView"+"/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
        )){ entry ->
            val id = if (entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            AddUserView(id,viewModel, navController)
        }
    }
}


