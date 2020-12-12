package com.bernatangg.testmukminapp.adapter

import ApiResponse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bernatangg.testmukminapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.model_article.view.*

class HomeAdapter (var listener: HomeListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var data : ArrayList<ApiResponse>?=null

    interface HomeListener {
        fun onItemClicked(apiResponse: ApiResponse, position: Int)
    }

    fun setData(list: ArrayList<ApiResponse>) {
        data = list
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: ApiResponse?) {
            itemView.tv_title.text = item?.title!!.rendered
            Glide.with(itemView.context)
                .load(item.jetpack_featured_media_url)
                .into(itemView.iv_article)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.model_article, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }
}