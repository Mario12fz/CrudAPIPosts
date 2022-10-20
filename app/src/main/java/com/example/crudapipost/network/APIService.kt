package com.example.crudapipost.network

import com.example.crudapipost.models.Posts
import retrofit2.Call
import retrofit2.http.*

interface APIService {


    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @POST
    fun savePosts(@Body posts: Posts): Call<Posts>

    @DELETE("posts/{id}")
    fun deletePosts(@Path("id") id: Int): Call<String>
}