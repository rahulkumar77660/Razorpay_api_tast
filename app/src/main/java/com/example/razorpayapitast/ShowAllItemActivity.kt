package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ShowAllItemActivity : AppCompatActivity(),RazorpayItemInteface {
    lateinit var myAdapter: ItemAdapter
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_item)

        recyclerView = findViewById(R.id.itemRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.razorpay.com/v1/")
            .build()

        val header = "Basic " + Base64.encodeToString("rzp_test_9JZiWARukAUmQR:25sPrA3Ppbb4Gn6nQO9OAj1s".toByteArray(),
            Base64.NO_WRAP)

        val hasMap = HashMap<String,String?>()
        hasMap["Authorization"]= header


        val razorpayApi = retrofit.create(RazorpayItemApi::class.java)
        val myCall = razorpayApi.getItemData(hasMap)
        myCall.enqueue(object : Callback<RazorpayItemDataResponse?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<RazorpayItemDataResponse?>,
                response: Response<RazorpayItemDataResponse?>
            ) {
                myAdapter = ItemAdapter(baseContext,response.body()!!.items,this@ShowAllItemActivity)
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter=myAdapter
            }

            override fun onFailure(call: Call<RazorpayItemDataResponse?>, t: Throwable) {

            }
        })


    }

    override fun onDelte(id: String, position: Int) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.razorpay.com/v1/")
            .build()

        val header = "Basic " + Base64.encodeToString("rzp_test_2XAQEnCY9O13rx:MpEmnhqgceL3PGIb6QZMvYgY".toByteArray(),
            Base64.NO_WRAP)

        val hasMap = HashMap<String,String?>()
        hasMap["Authorization"]= header

        val api = retrofit.create(RazorpayItemApi::class.java)
        val myCall = api.delteItemData(hasMap,id)
        myCall.enqueue(object : Callback<Void?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful){
                    myAdapter.notifyDataSetChanged()
                    (myAdapter.listItem as MutableList).removeAt(position)
                    myAdapter.notifyItemChanged(position)
                    Toast.makeText(this@ShowAllItemActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {

            }
        })
    }

    override fun onUpdate(id: String, item: String, des: String, price: Int, position: Int) {
        val intent = Intent(this@ShowAllItemActivity,ItemUpdateActivity::class.java)
        intent.putExtra("id",id.toString())
        intent.putExtra("item",item)
        intent.putExtra("des",des)
        intent.putExtra("price",price.toString())
        startActivity(intent)
    }
}