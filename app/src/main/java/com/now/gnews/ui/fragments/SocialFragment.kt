package com.now.gnews.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.now.gnews.R
import com.now.gnews.ui.MainActivity
import com.now.gnews.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_social.*

class SocialFragment : Fragment(R.layout.fragment_social) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        this.activity?.title = "Social Media"

        facebookCard.setOnClickListener { openUrl("https://facebook.com/") }
        instagramCard.setOnClickListener { openUrl("https://instagram.com/")}
        twitterCard.setOnClickListener { openUrl("https://twitter.com/")}
        youTubeCard.setOnClickListener { openUrl("https://youtube.com/")}
    }

    private fun openUrl(Url: String) {

        val uri: Uri = Uri.parse(Url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}