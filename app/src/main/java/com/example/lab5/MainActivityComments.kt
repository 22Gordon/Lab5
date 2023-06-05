package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.adapter.CommentAdapter
import com.example.lab5.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityComments : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentsList: List<Comment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_comments)

        recyclerView = findViewById(R.id.recyclerViewComments)
        commentAdapter = CommentAdapter(emptyList())

        makeCommentsRequest()
    }

    private fun makeCommentsRequest() {
        val request = ServiceBuilder.buildService(EndpointsComments::class.java)
        val call = request.getComments()

        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    commentsList = response.body() ?: emptyList()
                    commentAdapter = CommentAdapter(commentsList)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivityComments)
                        adapter = commentAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@MainActivityComments, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getCommentsCount(view: View) {
        val count: Int = commentsList.size
        Toast.makeText(this@MainActivityComments, count.toString(), Toast.LENGTH_SHORT).show()
    }

    fun getHudsonEmailName(view: View) {
        val comment = commentsList.find { it.email == "Hudson.Blick@ruben.biz" }
        val name = comment?.name ?: "Name not found!"
        Toast.makeText(this@MainActivityComments, name, Toast.LENGTH_SHORT).show()
    }

    fun getComEmailsCount(view: View) {
        val emailsCount = commentsList.count { it.email.endsWith(".com") }
        Toast.makeText(this@MainActivityComments, emailsCount.toString(), Toast.LENGTH_SHORT).show()
    }
}