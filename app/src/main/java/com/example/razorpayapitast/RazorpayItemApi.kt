package com.example.razorpayapitast

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface RazorpayItemApi {
    @POST("items")
    fun postItemData(@HeaderMap reqHeader : HashMap<String,String?>,@Body reqBody : RazorpayItemDataMdoleRequest ) : Call<RazorpayItemDataResponse>

    @GET("items")
    fun getItemData(@HeaderMap reqHeader: Map<String, String?>) : Call<RazorpayItemDataResponse>

    @DELETE("items/{item_id}")
    fun delteItemData(@HeaderMap reqHeaderMap: Map<String,String?>,@Path("item_id") item_id : String) : Call<Void>

    @PATCH("items/{item_id}")
    fun patchItemData(@HeaderMap reqHeaderMap: Map<String, String?>,@Path("item_id") item_id: String,@Body reqBody:ItemUpdateModel) : Call<RazorpayItemDataResponse>

}