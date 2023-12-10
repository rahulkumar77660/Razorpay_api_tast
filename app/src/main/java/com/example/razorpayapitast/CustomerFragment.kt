package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CustomerFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_customer, container, false)

        val cName : EditText = view.findViewById(R.id.customerName)
        val cEmail : EditText = view.findViewById(R.id.customerEmail)
        val cContact : EditText = view.findViewById(R.id.customerContact)
        val cBtn : Button = view.findViewById(R.id.customersubnitBtn)
        val textView6 : TextView = view.findViewById(R.id.textView6)
        textView6.setOnClickListener {
            startActivity(Intent(requireContext(),ShowAllCustomerActivity::class.java))
        }

        val header = "Basic " + Base64.encodeToString("rzp_test_9JZiWARukAUmQR:25sPrA3Ppbb4Gn6nQO9OAj1s".toByteArray(),
            Base64.NO_WRAP)

        val hasMap = HashMap<String,Any>()
        hasMap["Authorization"]= header

        cBtn.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.razorpay.com/v1/")
                .build()

            val data = CustomerRequestDataModel(cContact.text.toString(),cEmail.text.toString(),"1","12ABCDE2356F7GH",cName.text.toString(),
                NotesX("Hii","Hii")
            )
            val api = retrofit.create(RazorpayCustomerApi::class.java)
            api.postData(data,hasMap).enqueue(object : Callback<CustomerResponeDataModel?> {
                override fun onResponse(
                    call: Call<CustomerResponeDataModel?>,
                    response: Response<CustomerResponeDataModel?>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), response.code().toString(), Toast.LENGTH_SHORT).show()
                        cName.text.clear()
                        cEmail.text.clear()
                        cContact.text.clear()
                        cName.requestFocus()
                    }

                }

                override fun onFailure(call: Call<CustomerResponeDataModel?>, t: Throwable) {

                }
            })

        }




        return view
    }


}