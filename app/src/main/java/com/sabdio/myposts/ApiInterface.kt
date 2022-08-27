package com.sabdio.myposts

import android.provider.CallLog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/posts/{postId}")
    fun getPostById(@Path("postId") post: Int): Call<Post>
//    @GET("/posts/{post}/comments")
//    fun getComments(@Path("postI")): Call<List<Comment>>


    @GET("/posts/{postId}/comments")
    fun getComments(@Path("postId")comments:Int):Call<List<Comment>>
}

