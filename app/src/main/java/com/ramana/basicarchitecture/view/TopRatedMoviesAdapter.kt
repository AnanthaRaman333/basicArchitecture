package com.ramana.basicarchitecture.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ramana.basicarchitecture.R
import com.ramana.basicarchitecture.data.model.Result


class TopRatedMoviesAdapter(val context : Context) : ListAdapter<Result, TopRatedMoviesAdapter.TopRatedMoviesHolder>(TopRatedMoviesDiffCallback()) {

    private val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_rated_movie, parent, false) as View

        return TopRatedMoviesHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopRatedMoviesHolder, position: Int) {
        holder.movieTitle?.text = getItem(position).title
        holder.movieDesc?.text = getItem(position).overview
        Glide.with(context).load(BASE_URL_IMG+getItem(position).posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                .centerCrop()
                .crossFade().
                into(holder.posterImage)
    }

    class TopRatedMoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitle: TextView? = null
        var movieDesc: TextView? = null
        var posterImage: ImageView? = null

        init {
            movieTitle = itemView.findViewById(R.id.movie_title)
            movieDesc = itemView.findViewById(R.id.movie_desc)
            posterImage = itemView.findViewById(R.id.movie_poster)
        }

    }
}

private class TopRatedMoviesDiffCallback : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}