package com.example.clintproject.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilites {
   private val BASE_URL="https://smartgram.greymatterworks.in/"

    fun getInstant():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}