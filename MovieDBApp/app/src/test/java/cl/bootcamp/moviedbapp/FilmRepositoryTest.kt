package cl.bootcamp.moviedbapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import cl.bootcamp.moviedbapp.data.ApiMovies
import cl.bootcamp.moviedbapp.model.Film
import cl.bootcamp.moviedbapp.model.FilmDao
import cl.bootcamp.moviedbapp.repository.MovieRepositoryImpl
import cl.bootcamp.moviedbapp.util.Constants.Companion.API_KEY
import cl.bootcamp.moviedbapp.util.Constants.Companion.ENDPOINT_MOVIES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

private val film1 = Film(id = 1, title = "Film1", "posterPath1", "releaseDate1", 1.0)
private val film2 = Film(id = 2, title = "Film2", "posterPath2", "releaseDate2", 2.0)

class FilmRepositoryTest {


    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }

    private val restDataSource = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiMovies::class.java)

    private val newsRepository = MovieRepositoryImpl(restDataSource, MockFilmDao())

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Verificar si obtenemos correctamente los usuarios de la DB`() = runBlocking {
        val users = newsRepository.getAllFilms().first()
        assertEquals(2, users.size)
    }

    @Test
    fun `Verificar eliminacion de pelicula`() = runBlocking {
        newsRepository.deleteFilm(film1)
        val films = newsRepository.getAllFilms().first()
        assertEquals(1, films.size)
    }
}




    class MockFilmDao : FilmDao {
        private val films = MutableStateFlow<List<Film>>(listOf(film1, film2))

        override fun getAllFilms(): Flow<List<Film>> = films

        override fun getFilmById(id: Int): Film {
            TODO("Not yet implemented")
        }

        override fun insertFilm(film: Film) {
            films.value = films.value.toMutableList().apply { add(film) }
        }

        override fun delete(film: Film) {
            films.value = films.value.toMutableList().apply { remove(film) }
        }

        override suspend fun deleteAll() {
            TODO("Not yet implemented")
        }


    }


    val myDispatcher: Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/$ENDPOINT_MOVIES$API_KEY" -> MockResponse().apply { addResponse("api_list.json") }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }

    fun MockResponse.addResponse(filePath: String): MockResponse {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val source = inputStream?.source()?.buffer()
        source?.let {
            setResponseCode(200)
            setBody(it.readString(StandardCharsets.UTF_8))
        }
        return this
    }
