package cl.bootcamp.moviedbapp.state

import androidx.room.ColumnInfo

data class MovieState(
var title: String="",
var posterPath:String="",
var releaseDate:String="",
var voteAverage:Double=0.0

)