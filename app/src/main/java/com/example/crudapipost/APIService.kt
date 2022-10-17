package com.example.crudapipost

import com.example.crudapipost.models.Posts
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {


    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @POST
    fun savePosts(@Body posts: Posts): Call<Posts>
}