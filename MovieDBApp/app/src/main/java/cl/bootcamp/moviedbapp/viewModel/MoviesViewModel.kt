package cl.bootcamp.moviedbapp.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.moviedbapp.model.Film
import cl.bootcamp.moviedbapp.model.Movie
import cl.bootcamp.moviedbapp.repository.MovieRepository
import cl.bootcamp.moviedbapp.state.MovieState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepo: MovieRepository


    ) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()


    val films: Flow<List<Film>> by lazy {
        movieRepo.getAllFilms()
    }

    var state by mutableStateOf(MovieState())
        private set


    private val _currentIndex = MutableStateFlow(0)
    val currentIndex=_currentIndex.asStateFlow()

    fun incrementCurrentIndex() {
        _currentIndex.value++
    }

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = movieRepo.getMovies()
                _movies.value= result ?: emptyList()

                Log.d("datos api", "Movies: ${movies.value}")
            }
        }
    }




     fun addFilm(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list=_movies.value
                val movie = list[id]
                val film = Film(
                    title = movie.original_title,
                    posterPath =movie.poster_path,
                    releaseDate = movie.release_date,
                    voteAverage = movie.vote_average
                )
                movieRepo.addFilm(film)
                Log.d("MoviesViewModel", "Film added: ${movieRepo.getFilmById(id)}")
            }
        }
    }



    fun getFilm(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val mov = movieRepo.getFilmById(id)

                state = state.copy(
                    title = mov.title,
                    posterPath = mov.posterPath,
                    releaseDate = mov.releaseDate,
                    voteAverage = mov.voteAverage


                )

            }
        }

    }


    //Usar corrutinas!!!
    fun deleteFilm(film: Film) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.deleteFilm(film)
        }
    }


    fun cleanState() {
        state = MovieState()
    }


}



