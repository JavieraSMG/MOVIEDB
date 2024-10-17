package cl.bootcamp.moviedbapp.data

import cl.bootcamp.moviedbapp.model.ApiResponse
import cl.bootcamp.moviedbapp.util.Constants.Companion.API_KEY
import cl.bootcamp.moviedbapp.util.Constants.Companion.BASE_IMAGE
import cl.bootcamp.moviedbapp.util.Constants.Companion.ENDPOINT_MOVIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMovies {

    @GET(ENDPOINT_MOVIES + API_KEY)
    suspend fun getMovies(): Response<ApiResponse>




    @GET("$BASE_IMAGE/{poster_path}")
    suspend fun getImage(@Path(value = "poster_path") posterPath: String): ApiResponse





}