package com.now.gnews.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.now.gnews.R
import com.now.gnews.ui.MainActivity
import com.now.gnews.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article_view.*

class ArticleFragment : Fragment(R.layout.fragment_article_view) {
   lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        val article = args.article
        articleWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }


}