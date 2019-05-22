package com.ramana.basicarchitecture.base

import com.ramana.basicarchitecture.data.model.TopRatedMovies

interface BaseRepository {

    suspend fun getTopRatedMovies() : TopRatedMovies
}