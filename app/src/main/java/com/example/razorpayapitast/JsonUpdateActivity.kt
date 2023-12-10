package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonUpdateActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_update)

        val title : TextView = findViewById(R.id.updateJosnTitle)
        val id : TextView = findViewById(R.id.updateJosnId)
        val body : TextView = findViewById(R.id.updateJsonBody)
        val button : Button = findViewById(R.id.updateJosnBtn)

        val intentId = intent.getStringExtra("id")
        val intenttitle = intent.getStringExtra("title")
        val intentbody = intent.getStringExtra("body")

        title.setText(intenttitle)
        body.setText(intentbody)
        id.setText(intentId)

        button.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            val jsonApi = retrofit.create(JsonholderApi::class.java)
            jsonApi.updateData(intentId.toString().toInt()).enqueue(object :
                Callback<JsonplaceholderDataModeForGetItem?> {
                override fun onResponse(
                    call: Call<JsonplaceholderDataModeForGetItem?>,
                    response: Response<JsonplaceholderDataModeForGetItem?>
                ) {
                   if (response.isSuccessful){
                       Toast.makeText(this@JsonUpdateActivity, response.code().toString(), Toast.LENGTH_SHORT).show()

                   }
                }

                override fun onFailure(
                    call: Call<JsonplaceholderDataModeForGetItem?>,
                    t: Throwable
                ) {

                }
            })


        }

    }
}