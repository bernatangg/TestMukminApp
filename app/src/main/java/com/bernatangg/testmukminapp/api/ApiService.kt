package com.bernatangg.testmukminapp.api

import ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("posts/")
    fun fetchAllPosts(@Query("tags") id:Int):Call<List<ApiResponse>>

}