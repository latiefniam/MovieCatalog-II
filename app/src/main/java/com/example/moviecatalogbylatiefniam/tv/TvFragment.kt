package com.example.moviecatalogbylatiefniam.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogbylatiefniam.databinding.FragmentTvBinding
import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory

import com.example.tvcatalogbylatiefniam.tv.TvAdapter

class TvFragment: Fragment(){
    private lateinit var binding: FragmentTvBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(layoutInflater,container,false)
        return binding.root  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[TvVm::class.java]
            //val tvshow = viewModel.getTvShows()
            val tvAdapter= TvAdapter()

            viewModel.getTvShows().observe(viewLifecycleOwner,
                {tvshow->
            tvAdapter.setTv(tvshow)
                    tvAdapter.notifyDataSetChanged()
                })



            with(binding.recyclerviewtv){
                layoutManager =LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvAdapter
            }
        }
    }

}
