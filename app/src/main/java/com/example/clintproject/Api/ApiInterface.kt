package com.example.clintproject.Api

import com.example.clintproject.Class.JsonClass
import retrofit2.Response
import retrofit2.http.GET
//https://smartgram.greymatterworks.in/api/notification-list.php
interface ApiInterface {
    @GET("/notification-list.php")
    suspend fun getUsers():Response<JsonClass>
}