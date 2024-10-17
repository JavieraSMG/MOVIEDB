package cl.bootcamp.moviedbapp.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.moviedbapp.data.ApiMovies
import cl.bootcamp.moviedbapp.data.DbDataSource
import cl.bootcamp.moviedbapp.model.FilmDao
import cl.bootcamp.moviedbapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiMovies {
        return retrofit.create(ApiMovies::class.java)
    }

    @Provides
    @Singleton
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(context, DbDataSource::class.java, "films")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFilmDao(db: DbDataSource): FilmDao = db.filmDao()



}