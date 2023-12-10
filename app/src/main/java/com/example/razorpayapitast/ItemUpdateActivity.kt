package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ItemUpdateActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_update)

        val itemName : EditText = findViewById(R.id.updateItemName)
        val itemDes : EditText = findViewById(R.id.updateItemDes)
        val itemAmount : EditText = findViewById(R.id.updateItemAmount)
        val upadatebtn : Button = findViewById(R.id.itemUpdateBtn)

        val iId = intent.getStringExtra("id")
        val iname = intent.getStringExtra("item")
        val ides= intent.getStringExtra("des")
        val iprice = intent.getStringExtra("price")

        itemName.setText(iname)
        itemDes.setText(ides)
        itemAmount.setText(iprice)

        upadatebtn.setOnClickListener {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.razorpay.com/v1/")
                .build()

            val header = "Basic " + Base64.encodeToString("rzp_test_9JZiWARukAUmQR:25sPrA3Ppbb4Gn6nQO9OAj1s".toByteArray(),
                Base64.NO_WRAP)

            val hasMap = HashMap<String,String?>()
            hasMap["Authorization"]= header

            val data = ItemUpdateModel(itemAmount.text.toString().toInt(),"INR",itemDes.text.toString(),itemName.text.toString())

            val api = retrofit.create(RazorpayItemApi::class.java)
            val myCall = api.patchItemData(hasMap,iId!!,data)
            myCall.enqueue(object : Callback<RazorpayItemDataResponse?> {
                override fun onResponse(
                    call: Call<RazorpayItemDataResponse?>,
                    response: Response<RazorpayItemDataResponse?>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@ItemUpdateActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@ItemUpdateActivity,ShowAllItemActivity::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<RazorpayItemDataResponse?>, t: Throwable) {

                }
            })


        }
    }

}