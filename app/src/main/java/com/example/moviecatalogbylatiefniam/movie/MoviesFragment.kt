package com.example.moviecatalogbylatiefniam.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogbylatiefniam.databinding.FragmentMoviesBinding
import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory


class MoviesFragment: Fragment(){

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[MoviesVm::class.java]
        //val movies = viewModel.getMovies()

            val adapterMovie = MoviesAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, {
                movie ->
                adapterMovie.setMovie(movie)
                adapterMovie.notifyDataSetChanged()
            })


            with(binding.recyclerviewmovie){
                layoutManager =LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapterMovie
            }
        }
    }

}