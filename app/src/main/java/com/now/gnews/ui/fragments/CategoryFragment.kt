package com.now.gnews.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.now.gnews.R
import com.now.gnews.adapters.NewsAdapter
import com.now.gnews.ui.MainActivity
import com.now.gnews.ui.NewsViewModel
import com.now.gnews.wrapper.Resource
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment(R.layout.fragment_category) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter


    val args: CategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setRecyclerView()
        val category = args.CateArgs.category
        this.activity?.title = category
        CateFragmentprogressBar.visibility = View.VISIBLE

        viewModel.getNewsFromCategory("us", category)

        viewModel.categoryNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    CateFragmentprogressBar.visibility = View.INVISIBLE
                    response.data?.let { nResponse ->
                        newsAdapter.differ.submitList(nResponse.articles)
                    }
                }
                is Resource.Error -> {
                    CateFragmentprogressBar.visibility = View.INVISIBLE
                    response.message?.let { message ->
                        Toast.makeText(activity, "Error $message", Toast.LENGTH_LONG)
                            .show()

                    }
                }
                is Resource.Loading -> {
                    CateFragmentprogressBar.visibility = View.VISIBLE
                }
            }
        })

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_categoryFragment_to_articleFragment,
                bundle)
        }
    }

    private fun setRecyclerView() {

        newsAdapter = NewsAdapter()
        catergoryRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}