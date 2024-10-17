package cl.bootcamp.moviedbapp.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cl.bootcamp.moviedbapp.R
import cl.bootcamp.moviedbapp.components.MovCard
import cl.bootcamp.moviedbapp.viewModel.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: MoviesViewModel= hiltViewModel(),
    navController: NavController
) {


    val movies by viewModel.movies.collectAsState(listOf())
    val films by viewModel.films.collectAsState(listOf())
    val total=movies.size
    val currentIndex by viewModel.currentIndex.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.MainTitle), color = Color.White) },
                actions = {
                    IconButton(
                        onClick = {
                            if (currentIndex < total) {
                                viewModel.addFilm(currentIndex)
                                viewModel.incrementCurrentIndex()
                                println("currentIndex: $currentIndex")
                            }
                        }
                    ) {
                        Icon(Icons.Default.Add, "Add", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF171B30)
                )
            )
        }
    ) { innerPadding ->
        //val movies by viewModel.movies.collectAsState(listOf())

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(films) { item ->
                MovCard(
                    item,
                    onDelete = {viewModel.deleteFilm(item)},
                    onClick = {navController.navigate("DetailsView/${item.id}")}

                )

            }
        }
    }

}



