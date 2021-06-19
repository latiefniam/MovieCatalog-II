package com.example.moviecatalogbylatiefniam.di

import android.content.Context
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.data.source.remote.RemoteDataSource
import com.example.moviecatalogbylatiefniam.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context):Repository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return Repository.getInstance(remoteDataSource)
    }
}