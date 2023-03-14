package com.example.memes

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/gimme/wholesomememes")
    fun getData(): Call<responseDataClass>
}