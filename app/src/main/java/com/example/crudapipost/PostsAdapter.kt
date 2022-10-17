package com.example.crudapipost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapipost.models.Posts

class PostsAdapter(private val postsList: List<Posts>): RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(layoutInflater.inflate(R.layout.item_posts, parent, false))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = postsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = postsList.size

}


