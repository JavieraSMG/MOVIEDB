package cl.bootcamp.moviedbapp.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cl.bootcamp.moviedbapp.model.Film
import cl.bootcamp.moviedbapp.model.Movie
import coil.compose.AsyncImage

@Composable
fun MovCard(movie: Film, onDelete: () -> Unit={},onClick:()->Unit={}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick=onClick,
        colors = CardColors(
            containerColor = Color(0xFF5E6DC9),
            contentColor = androidx.compose.ui.graphics.Color.Black ,
             disabledContainerColor = androidx.compose.ui.graphics.Color.Gray,
            disabledContentColor = androidx.compose.ui.graphics.Color.Black
        )
    )
    {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Release Date: ${movie.releaseDate}")
                Text(text = "Rating: ${movie.voteAverage}")
            }

            IconButton(
                onClick = onDelete,
                modifier=Modifier
                    .testTag("deleteButton"
                )
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
    }
}