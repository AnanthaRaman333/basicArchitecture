package com.ramana.basicarchitecture.base

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import com.ramana.basicarchitecture.MainApplication

abstract class BaseViewModel<out T : BaseRepository>(val repository : T, val appContext : Application = MainApplication.getApplication()) : AndroidViewModel(appContext)  {


    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }
}