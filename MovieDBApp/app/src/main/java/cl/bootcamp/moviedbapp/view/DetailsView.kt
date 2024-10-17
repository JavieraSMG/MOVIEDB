import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.moviedbapp.R
import cl.bootcamp.moviedbapp.components.MovCard
import cl.bootcamp.moviedbapp.components.TopBarComponent
import cl.bootcamp.moviedbapp.viewModel.MoviesViewModel
import coil.compose.AsyncImage

@Composable
fun DetailsView(
    viewModel: MoviesViewModel,
    navController: NavController,
    id:Int
) {


    Scaffold(
        topBar = {
            TopBarComponent(
                titulo = stringResource(R.string.Detail),
                mostrarBotton = true
            ) {
                viewModel.cleanState()
                navController.popBackStack()
            }
        }
    ) {
        ContentDetailView(it, viewModel,id)
    }
}

@Composable
fun ContentDetailView(
    paddingValues: PaddingValues,
    viewModel: MoviesViewModel,
    id:Int
){

    LaunchedEffect(Unit){
        viewModel.getFilm(id)
    }


    val state=viewModel.state
    val title=state.title
    val image=state.posterPath
    val rel=state.releaseDate
    val vote=state.voteAverage

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        colors = CardColors(
            containerColor = Color(0xFF5A7199),
            contentColor = androidx.compose.ui.graphics.Color.Black ,
            disabledContainerColor = androidx.compose.ui.graphics.Color.Gray,
            disabledContentColor = androidx.compose.ui.graphics.Color.Black
        )

    )
    {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()

            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {


                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Release Date: $rel")
                Text(text = "Rating: $vote")
            }

        }
    }

}
