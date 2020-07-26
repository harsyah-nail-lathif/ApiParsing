package com.example.apiparsing.service

import com.example.apiparsing.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

object RetrofitBuilder{
    private val client= OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadline::class.java)
}

interface TopHeadline{
    @Headers("Authorization: ce315bb914ab44d7866da6788eeed502")
    @GET ("/v2/top-headlines?country=id")
    fun fetchHeadlines(): Call<ResponseNews>
}