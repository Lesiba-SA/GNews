package com.now.gnews.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
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
        articleWebView.webViewClient = WebViewClient()
        articleWebView.loadUrl(article.url)
        ArticleViewprogressBar.visibility = View.VISIBLE
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            url: String
        ): Boolean {
            view?.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            ArticleViewprogressBar.visibility = View.INVISIBLE
        }
    }



}