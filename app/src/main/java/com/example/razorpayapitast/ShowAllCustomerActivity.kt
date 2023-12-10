package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShowAllCustomerActivity : AppCompatActivity(),CustomerInterListener {
    lateinit var myAdapter: CustomerAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_customer)

        recyclerView = findViewById(R.id.customerRecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        val header = "Basic " + Base64.encodeToString("rzp_test_9JZiWARukAUmQR:25sPrA3Ppbb4Gn6nQO9OAj1s".toByteArray(), Base64.NO_WRAP)
        val hasMap = HashMap<String,Any>()
        hasMap["Authorization"]= header
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.razorpay.com/v1/")
            .build()

        val api = retrofit.create(RazorpayCustomerApi::class.java)
        api.getData(hasMap).enqueue(object : Callback<CustomerResponeDataModel?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<CustomerResponeDataModel?>,
                response: Response<CustomerResponeDataModel?>
            ) {
               if (response.isSuccessful){
                   myAdapter= CustomerAdapter(baseContext,response.body()!!.items,this@ShowAllCustomerActivity)
                   myAdapter.notifyDataSetChanged()
                   recyclerView.adapter=myAdapter
               }
            }

            override fun onFailure(call: Call<CustomerResponeDataModel?>, t: Throwable) {

            }
        })

    }

    override fun update(id: String, position: Int, name: String, email: String, contact: String) {
        val intent = Intent(this@ShowAllCustomerActivity,CustomerUpdateActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("name",name)
        intent.putExtra("email",email)
        intent.putExtra("contact",contact)
        startActivity(intent)
    }


}