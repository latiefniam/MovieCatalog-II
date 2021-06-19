package com.example.moviecatalogbylatiefniam.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.moviecatalogbylatiefniam.SectionsPagerAdapter
import com.example.moviecatalogbylatiefniam.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPager = SectionsPagerAdapter(this, supportFragmentManager)
        binding.pager.adapter = sectionPager
        binding.tabs.setupWithViewPager(binding.pager)

        supportActionBar?.elevation=0f


    }
}