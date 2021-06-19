package com.example.moviecatalogbylatiefniam.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.detail.DetailVM
import com.example.moviecatalogbylatiefniam.di.Injection
import com.example.moviecatalogbylatiefniam.movie.MoviesVm
import com.example.moviecatalogbylatiefniam.tv.TvVm

class ViewModelFactory private constructor(private val mRepository: Repository)
    :ViewModelProvider.NewInstanceFactory(){

        companion object{
            @Volatile
            private var instance : ViewModelFactory? = null

            fun getInstance (context: Context): ViewModelFactory
            = instance?: synchronized(this){
                instance?: ViewModelFactory(Injection.provideRepository(
                    context)).apply {
                        instance=this

                }
            }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MoviesVm::class.java)->
            {MoviesVm(mRepository)as T}
            modelClass.isAssignableFrom(TvVm::class.java)->
            {TvVm(mRepository)as T}
            modelClass.isAssignableFrom(DetailVM::class.java)->
            {DetailVM(mRepository)as T}
            else -> throw Throwable("Unknow ViewModel class: "+ modelClass.name)
        }
    }
}