package com.now.gnews.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.now.gnews.R
import com.now.gnews.adapters.NewsAdapter
import com.now.gnews.ui.MainActivity
import com.now.gnews.ui.NewsViewModel
import com.now.gnews.wrapper.Resource
import kotlinx.android.synthetic.main.fragment_all_news.*

class AllNewsFragment : Fragment(R.layout.fragment_all_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)

            }
            findNavController()
                .navigate(R.id.action_allNewsFragment_to_articleFragment,
                bundle)
        }

        viewModel.allNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { nResponse ->
                        newsAdapter.differ.submitList(nResponse.articles)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->{
                        Toast.makeText(activity, "Error $message", Toast.LENGTH_LONG)
                            .show()
                    }

                    }
                }
                is Resource.Loading -> {

                }
            }
        })
    }

    private fun setRecyclerView() {

        newsAdapter = NewsAdapter()
        allNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}