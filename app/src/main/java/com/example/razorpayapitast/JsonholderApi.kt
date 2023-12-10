package com.example.razorpayapitast

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface JsonholderApi {

    @GET("posts")
    fun getData():Call<List<JsonplaceholderDataModeForGetItem>>

    @DELETE("posts/{item_id}")
    fun deleteData(@Path("item_id") itemId: Int) : Call<Void>

    @PUT("posts/{item_id}")
    fun updateData(@Path("item_id") itemId : Int) : Call<JsonplaceholderDataModeForGetItem>
}