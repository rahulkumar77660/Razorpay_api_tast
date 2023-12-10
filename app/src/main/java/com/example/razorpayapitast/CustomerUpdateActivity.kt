package com.example.razorpayapitast

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

class CustomerUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_update)

        val cusName : EditText = findViewById(R.id.updateCustomerName)
        val cusContact : EditText = findViewById(R.id.updateCustomerContact)
        val cusEmail : EditText = findViewById(R.id.updateCustomerEmail)
        val btn : Button = findViewById(R.id.customerUpdateBtn)

        val getId = intent.getStringExtra("id")
        val getName = intent.getStringExtra("name")
        val getEmail = intent.getStringExtra("email")
        val getContact = intent.getStringExtra("contact")

        cusName.setText(getName.toString())
        cusEmail.setText(getEmail.toString())
        cusContact.setText(getContact.toString())




        btn.setOnClickListener {
            Toast.makeText(this@CustomerUpdateActivity, "Hii", Toast.LENGTH_SHORT).show()
            val header = "Basic " + Base64.encodeToString("rzp_test_9JZiWARukAUmQR:25sPrA3Ppbb4Gn6nQO9OAj1s".toByteArray(),
                Base64.NO_WRAP)
            val hasMap = HashMap<String,String?>()
            hasMap["Authorization"]= header

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.razorpay.com/v1/")
                .addConverterFactory(GsonConverterFactory.create()) // Use Gson converter for JSON parsing, you can use other converters too
                .build()

            val data = CustomerPutModel(cusName.text.toString(),cusEmail.text.toString(),cusContact.text.toString())

            val api = retrofit.create(RazorpayCustomerApi::class.java)
            api.putData(hasMap,getId!!,data).enqueue(object : Callback<CustomerResponeDataModel?> {
                override fun onResponse(
                    call: Call<CustomerResponeDataModel?>,
                    response: Response<CustomerResponeDataModel?>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@CustomerUpdateActivity, response.code().toString(), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@CustomerUpdateActivity,ShowAllCustomerActivity::class.java))
                        finish()
                    }
                }

                override fun onFailure(call: Call<CustomerResponeDataModel?>, t: Throwable) {
                    Toast.makeText(this@CustomerUpdateActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })

        }


    }
}