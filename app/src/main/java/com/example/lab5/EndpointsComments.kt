package com.example.lab5

import retrofit2.Call
import retrofit2.http.*

interface EndpointsComments {
    @GET("comments")
    fun getComments(): Call<List<Comment>>
}