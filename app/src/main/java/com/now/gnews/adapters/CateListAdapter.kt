package com.now.gnews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.now.gnews.R
import com.now.gnews.data.CateListData
import kotlinx.android.synthetic.main.catelistcard.view.*

class CateListAdapter(private val cateList: List<CateListData>) : RecyclerView.Adapter<CateListAdapter.CateListViewHolder>() {



   inner class CateListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.cateImage
        val button: Button = itemView.cateButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateListViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.catelistcard,
        parent, false)

        return CateListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CateListViewHolder, position: Int) {
        val currentItem = cateList[position]
        holder.imageView.setImageResource(currentItem.imageId)
        holder.button.text = currentItem.category

        holder.button.setOnClickListener { onItemClickListener?.let { it(currentItem) } }
    }

    override fun getItemCount(): Int {
       return cateList.size
    }

    private var onItemClickListener: ((CateListData) -> Unit)? = null

    fun setOnItemClickListener(listener: (CateListData) -> Unit) {
        onItemClickListener = listener
    }
}