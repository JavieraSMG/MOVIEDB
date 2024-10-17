package cl.bootcamp.moviedbapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.moviedbapp.model.Film
import cl.bootcamp.moviedbapp.model.FilmDao

@Database(entities = [Film::class], version = 1)
abstract class DbDataSource: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}

