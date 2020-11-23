package com.now.gnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.now.gnews.R
import com.now.gnews.repository.NewsRepo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepo = NewsRepo()
        val viewModelProvideFactory = NewsViewModelProvider(newsRepo)
        viewModel = ViewModelProvider(this, viewModelProvideFactory).get(NewsViewModel::class.java)


        bottomNav.setupWithNavController(NavHostFragment.findNavController())

    }



}