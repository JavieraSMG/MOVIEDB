package cl.bootcamp.moviedbapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmDao {

    @Query("SELECT * FROM films ORDER BY id ")
    fun getAllFilms(): Flow<List<Film>>

    @Query("SELECT * FROM films WHERE id = :id")
     fun getFilmById(id: Int): Film

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertFilm(film: Film)

    @Delete
     fun delete(film: Film)

    @Query("DELETE FROM films")
    suspend fun deleteAll()
}

