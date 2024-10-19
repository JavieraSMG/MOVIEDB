package cl.bootcamp.mobistore.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cl.bootcamp.mobistore.components.CustomCard
import cl.bootcamp.mobistore.viewModel.PhoneViewModel

@Composable
fun HomeView(
    viewModel: PhoneViewModel= hiltViewModel(),
    navController:NavController
) {

    val phonesRoom by viewModel.phonesRoom.collectAsState(listOf())

    LazyColumn(

    ) {
        items(phonesRoom) {cell->
            CustomCard(cell){
                navController.navigate("DetailsView/${cell.id}")
            }
        }



    }
}