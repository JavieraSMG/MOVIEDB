package cl.bootcamp.moviedbapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "original_title") val title: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double

)
