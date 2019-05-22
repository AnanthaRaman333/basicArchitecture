package com.ramana.basicarchitecture.data.remote

import com.ramana.basicarchitecture.data.model.TopRatedMovies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") pageIndex: Int
    ): Deferred<TopRatedMovies>
}