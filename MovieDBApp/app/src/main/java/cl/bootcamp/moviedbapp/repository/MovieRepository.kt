package cl.bootcamp.moviedbapp.repository

import android.util.Log
import cl.bootcamp.moviedbapp.data.ApiMovies
import cl.bootcamp.moviedbapp.model.Film
import cl.bootcamp.moviedbapp.model.FilmDao
import cl.bootcamp.moviedbapp.model.Movie
import cl.bootcamp.moviedbapp.util.Constants.Companion.BASE_IMAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieRepository {

    //Peliculas de api
    suspend fun getMovies(): List<Movie>

    // Base de datos local
     fun deleteFilm(film: Film)
     fun getFilmById(id:Int):Film
     fun getAllFilms(): Flow<List<Film>>
      fun addFilm(film: Film)



    //  suspend fun getImage(posterPath: String): Movie

}

class MovieRepositoryImpl@Inject constructor(
    private val dataSource: ApiMovies,
    private val filmDao: FilmDao
) : MovieRepository{


    //agrega una pelicula a la base de datos
     override fun addFilm(film: Film) {
         filmDao.insertFilm(film)

     }




    //Respuesta api + /* almacenar cada pelicula en base de datos*/
    override suspend fun getMovies(): List<Movie> {
        val response = dataSource.getMovies()
        val movieList = response.body()?.results ?: emptyList()
        for (movie in movieList) {
            movie.poster_path = BASE_IMAGE + movie.poster_path
            Log.d("urlmodificada", "imagePath: ${movie.poster_path}")
          /*  val film = Film(
                title = movie.original_title,
                posterPath =movie.poster_path,
                releaseDate = movie.release_date,
                voteAverage = movie.vote_average
            )
            filmDao.insertFilm*/
        }
        return movieList

    }
    //Todos db
    override  fun getAllFilms(): Flow<List<Film>> {
        return filmDao.getAllFilms()
    }


    override fun deleteFilm(film: Film) {
        filmDao.delete(film)
    }

    override fun getFilmById(id: Int): Film {
       return filmDao.getFilmById(id)
    }



}




