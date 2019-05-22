package com.ramana.basicarchitecture

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.ramana.basicarchitecture.view.TopRatedMovieFragment


class MainActivity : AppCompatActivity() , TopRatedMovieFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.transaction(now = false, allowStateLoss = false) {
            replace(R.id.fragment_container, TopRatedMovieFragment.newInstance("test", "test"), "TAG")

        }
    }
}
