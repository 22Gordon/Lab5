package com.example.lab5.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.Comment
import com.example.lab5.R

class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val postId: TextView = itemView.findViewById(R.id.postId)
    private val id: TextView = itemView.findViewById(R.id.commentId)
    private val email: TextView = itemView.findViewById(R.id.commentEmail)
    private val name: TextView = itemView.findViewById(R.id.commentName)
    private val body: TextView = itemView.findViewById(R.id.body)

    fun bind(comment: Comment){
        postId.text = comment.postId.toString()
        id.text = comment.id.toString()
        email.text = comment.email
        name.text = comment.name
        body.text = comment.body
    }
}