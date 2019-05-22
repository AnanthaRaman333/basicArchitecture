package com.ramana.basicarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramana.basicarchitecture.base.BaseRepository
import com.ramana.basicarchitecture.data.model.Result
import com.ramana.basicarchitecture.data.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopRatedMoviesViewModel(private val repository: BaseRepository = RemoteRepository()) : ViewModel() {

    val topRatedMovies: MutableLiveData<List<Result>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            topRatedMovies.postValue(repository.getTopRatedMovies().results)
        }
    }
}