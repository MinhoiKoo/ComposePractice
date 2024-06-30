package com.minhoi.composepractice

import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("posts/1")
    suspend fun getPost1(): Response<Post>
}