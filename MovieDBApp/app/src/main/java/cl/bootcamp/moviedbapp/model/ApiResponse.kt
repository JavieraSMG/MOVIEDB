package cl.bootcamp.moviedbapp.model

data class ApiResponse(
    val page: Int,
    val results: List<Movie>,

    )

data class Movie (
    val original_title: String,
    var poster_path: String,
    val release_date: String,
    val vote_average: Double

)


/*
{
    "page": 1,
    "results": [
    {
        "adult": false,
        "backdrop_path": "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
        "genre_ids": [
        18,
        80
        ],
        "id": 278,
        "original_language": "en",
        "original_title": "The Shawshank Redemption",
        "overview": "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        "popularity": 132.077,
        "poster_path": "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
        "release_date": "1994-09-23",
        "title": "The Shawshank Redemption",
        "video": false,
        "vote_average": 8.707,
        "vote_count": 26896
    },
   */