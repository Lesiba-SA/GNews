package com.now.gnews.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.now.gnews.R
import com.now.gnews.adapters.CateListAdapter
import com.now.gnews.adapters.NewsAdapter
import com.now.gnews.data.CateList
import com.now.gnews.ui.MainActivity
import com.now.gnews.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category_list.*

class CategoryListFragment : Fragment(R.layout.fragment_category_list) {

    lateinit var viewModel: NewsViewModel
    lateinit var cateListAdapter: CateListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setRecyclerView()

        this.activity?.title = "Category List"

        cateListAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("CateArgs", it)

            }
            findNavController()
                .navigate(R.id.action_categoryListFragment_to_categoryFragment,
                    bundle)
        }


    }

    private fun setRecyclerView() {

        cateListAdapter = CateListAdapter(CateList.ListOfCate)
        cateListRecyclerView.apply {
            adapter = cateListAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}