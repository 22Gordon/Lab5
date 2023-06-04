package com.example.lab5.api

import retrofit2.Call
import retrofit2.http.*
interface EndPoints {
    @GET("/comments/")
    fun getComments(): Call<List<User>>

    @GET("/comments")
    fun getAllComments(): Call<List<User>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @FormUrlEncoded
    @POST("/posts")
    fun postTest(@Field("title") title: String?): Call<OutputPost>

}