package com.example.razorpayapitast

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RazorpayCustomerApi {

    @POST("customers")
    fun postData(@Body postRequest : CustomerRequestDataModel,@HeaderMap reqHeader : HashMap<String,Any>) : Call<CustomerResponeDataModel>

    @GET("customers")
    fun getData(@HeaderMap reqHeader:HashMap<String,Any>) : Call<CustomerResponeDataModel>

    @PUT("customers/{cust_id}")
    fun putData(@HeaderMap header: HashMap<String, String?>, @Path("cust_id") customerId: String, @Body putRequest: CustomerPutModel): Call<CustomerResponeDataModel>
}