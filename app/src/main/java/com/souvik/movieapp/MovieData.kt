package com.souvik.movieapp

data class MovieData(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>
)