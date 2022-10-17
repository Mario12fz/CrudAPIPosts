package com.example.crudapipost

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapipost.databinding.ItemPostsBinding
import com.example.crudapipost.models.Posts

class PostsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPostsBinding.bind(view)

    fun bind(posts: Posts){
        binding.postTitle.text = posts.title
        binding.postBody.text = posts.body
    }
}