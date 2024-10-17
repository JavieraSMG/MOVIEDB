package cl.bootcamp.moviedbapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import cl.bootcamp.moviedbapp.model.Movie
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.moviedbapp.model.Film
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    titulo: String,
    mostrarBotton: Boolean = false,
    onClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = titulo, color = Color.White, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1A0777)
        ),
        navigationIcon = {
            if (mostrarBotton) {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        "",
                        tint = Color.White
                    )
                }
            }
        }
    )
}







//Trabaja con lista local
@Composable
fun FilmCard(
    film: Film,
    onClick: () -> Unit
) {
    Card(

        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Text(text = film.title)
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            model = film.posterPath,
            contentDescription = null
        )

    }
}



//Prueba para verificar que llegan todos los datos de la api
@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Card(

        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() }
    ) {
       Text(text = movie.original_title)
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            model = "{${movie.poster_path}}",
            contentDescription = null
        )

    }
}



