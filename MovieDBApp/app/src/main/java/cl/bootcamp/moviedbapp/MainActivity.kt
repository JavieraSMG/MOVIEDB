package cl.bootcamp.moviedbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import cl.bootcamp.moviedbapp.navigation.NavManager
import cl.bootcamp.moviedbapp.ui.theme.MovieDBAppTheme
import cl.bootcamp.moviedbapp.view.HomeView
import cl.bootcamp.moviedbapp.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MoviesViewModel by viewModels()
        setContent {
            MovieDBAppTheme {
               NavManager(viewModel = viewModel)

                }
            }
        }
    }


