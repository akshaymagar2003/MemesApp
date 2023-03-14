package com.example.memes

import retrofit2.http.GET

interface ApiInterface {
    @GET("/gimme")
    fun getData()
}