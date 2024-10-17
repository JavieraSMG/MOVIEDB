package cl.bootcamp.moviedbapp.utils

import cl.bootcamp.moviedbapp.di.RepositoryModule
import cl.bootcamp.moviedbapp.model.Film
import cl.bootcamp.moviedbapp.model.Movie
import cl.bootcamp.moviedbapp.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.nio.file.Files.delete
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)



class FakeRepositoryModule {

    @Singleton
    @Provides
    fun provideFakeRepository(): MovieRepository=object : MovieRepository {

        private val films = MutableStateFlow<List<Film>>(emptyList())

        val mov1=Movie("M1","M1","M1",0.0)
        val mov2=Movie("M2","M2","M2",0.0)
        val mov3=Movie("M3","M3","M3",0.0)

        private val movies = listOf(mov1, mov2, mov3)

        override suspend fun getMovies(): List<Movie> {
            return movies

        }

        override fun deleteFilm(film: Film) {
            films.value = films.value.toMutableList().apply { remove(film) }
        }

        override fun getFilmById(id: Int): Film {
            TODO("Not yet implemented")
        }

        override fun getAllFilms(): Flow<List<Film>> {
            return films
        }

        override fun addFilm(film: Film) {
            films.value = films.value.toMutableList().apply { add(film) }




        }


    }
}