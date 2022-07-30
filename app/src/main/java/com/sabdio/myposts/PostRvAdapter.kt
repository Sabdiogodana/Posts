package com.sabdio.myposts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sabdio.myposts.databinding.PostsListItemBinding

class PostRvAdapter(var context: Context,    var posts: List<Post>): RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var binding = PostsListItemBinding.inflate(LayoutInflater.from(context),parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var currentPost = posts.get(position)
        with(holder.binding){
            tvUserId.text =  currentPost.userId.toString()
            tvId.text = currentPost.id.toString()
            tvTitle.text = currentPost.title
            tvBody.text = currentPost.body
        }
    }

    override fun getItemCount(): Int {
        return  posts.size
    }
}



class PostsViewHolder(val binding: PostsListItemBinding): RecyclerView.ViewHolder(binding.root)