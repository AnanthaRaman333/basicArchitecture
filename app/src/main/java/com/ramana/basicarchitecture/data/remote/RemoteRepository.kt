package com.ramana.basicarchitecture.data.remote

import com.ramana.basicarchitecture.base.BaseRepository
import com.ramana.basicarchitecture.data.model.TopRatedMovies

class RemoteRepository(private val apiService: MovieService = RetrofitClient.getRetrofitClient()) : BaseRepository {

    override suspend fun getTopRatedMovies(): TopRatedMovies {
        return apiService.getTopRatedMovies("ec01f8c2eb6ac402f2ca026dc2d9b8fd", "en-US", 1).await()
    }

}