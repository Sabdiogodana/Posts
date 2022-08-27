package com.sabdio.myposts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sabdio.myposts.databinding.CommentsListItemBinding


class CommentsRVAdapter(var comments:List <Comment>): RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var binding = CommentsListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    return CommentsViewHolder(binding)
}


    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComment = comments.get(position)
        with(holder.binding){
        tvpostId.text = currentComment.postId.toString()
        tvid.text = currentComment.id.toString()
        tvname.text = currentComment.name
        tvemail.text = currentComment.email
        tvbody.text = currentComment.body

    }
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}

class CommentsViewHolder(val binding: CommentsListItemBinding): RecyclerView.ViewHolder(binding.root)


