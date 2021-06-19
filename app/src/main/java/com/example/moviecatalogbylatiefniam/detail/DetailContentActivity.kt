package com.example.moviecatalogbylatiefniam.detail

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogbylatiefniam.R
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.TvEntity
import com.example.moviecatalogbylatiefniam.databinding.ActivityDetailContentBinding
import com.example.moviecatalogbylatiefniam.databinding.ContentDetailBinding
import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory

class DetailContentActivity : AppCompatActivity() {
    


    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
    private lateinit var bindingContentDetail: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailContentBinding.inflate(layoutInflater)
        bindingContentDetail= binding.detailContent
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)

        val viewModel = ViewModelProvider(this,factory)[
                DetailVM::class.java]

        val extra = intent.extras
        if(extra != null){
            val movId = extra.getString(EXTRA_MOVIE)
            val tvId = extra.getString(EXTRA_TVSHOW)
            if (movId!=null){
                viewModel.movSelected(movId)
                viewModel.getMovies().observe(this,{
                    movie-> populatedMov(movie)
                })

            }
            if (tvId!=null){
                viewModel.tvSelected(tvId)
                viewModel.getTvShow().observe(this,{
                    tvshow -> populatedTv(tvshow)
                })
            }
        }

    }
    private fun populatedMov(data: MovieEntity){
        bindingContentDetail.title.text = data.movieTitle
        bindingContentDetail.descriptionContent.text= data.movieDescription
        bindingContentDetail.genre.text=data.movieGenre
        bindingContentDetail.duration.text=data.movieDuration
        Glide.with(this).load(data.moviePicture)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(
                R.drawable.ic_baseline_error_24
            ))
            .into(bindingContentDetail.image)
    }
    private fun populatedTv(data: TvEntity){
        bindingContentDetail.title.text = data.tvTitle
        bindingContentDetail.descriptionContent.text= data.tvDescription
        bindingContentDetail.genre.text=data.tvGenre
        bindingContentDetail.duration.text=data.tvDuration
        Glide.with(this).load(data.tvPicture)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(
                R.drawable.ic_baseline_error_24
            ))
            .into(bindingContentDetail.image)
    }


}