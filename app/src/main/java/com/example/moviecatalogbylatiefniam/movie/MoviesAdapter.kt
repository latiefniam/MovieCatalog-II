package com.example.moviecatalogbylatiefniam.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.databinding.ItemContentBinding
import com.example.moviecatalogbylatiefniam.detail.DetailContentActivity

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var list = ArrayList<MovieEntity>()
    fun setMovie(movies: List<MovieEntity>?){
        if (movies == null) return
        this.list.clear()
        this.list.addAll(movies)
    }
    class ViewHolder(private val binding: ItemContentBinding):RecyclerView.ViewHolder
        (binding.root){
            fun bind (movies: MovieEntity){
                with(binding){
                    title.text = movies.movieTitle
                    genre.text = movies.movieGenre
                    duration.text = movies.movieDuration
                    Glide.with(itemView.context)
                        .load(movies.moviePicture)
                        .into(image)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailContentActivity::class.java)
                        intent.putExtra(DetailContentActivity.EXTRA_MOVIE,movies.movieId)
                        itemView.context.startActivity(intent)
                    }
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = list[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = list.size
}