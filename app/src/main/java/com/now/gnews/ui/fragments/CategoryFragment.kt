package com.now.gnews.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.now.gnews.R
import com.now.gnews.ui.MainActivity
import com.now.gnews.ui.NewsViewModel

class CategoryFragment : Fragment(R.layout.fragment_category) {

    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}