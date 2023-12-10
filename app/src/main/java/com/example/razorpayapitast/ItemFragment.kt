package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class ItemFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_item, container, false)

        val itemName : TextView = view.findViewById(R.id.ItemName)
        val itemDes : TextView = view.findViewById(R.id.itemDes)
        val itemAmount : TextView = view.findViewById(R.id.itemAmount)
        val itemBtn : TextView = view.findViewById(R.id.submitBtnItem)

        val header = "Basic " + Base64.encodeToString("rzp_test_9JZiWARukAUmQR:25sPrA3Ppbb4Gn6nQO9OAj1s".toByteArray(),Base64.NO_WRAP)

        val hasMap = HashMap<String,String?>()
        hasMap["Authorization"]= header

        itemBtn.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.razorpay.com/v1/")
                .build()

            val amount = itemAmount.text.toString()
            val name = itemName.text.toString()
            val des = itemDes.text.toString()

            val data = RazorpayItemDataMdoleRequest(amount.toInt(),"INR",des,name)
            val razorpayApi = retrofit.create(RazorpayItemApi::class.java)
            val myCall = razorpayApi.postItemData(hasMap,data)
            myCall.enqueue(object : Callback<RazorpayItemDataResponse?> {
                override fun onResponse(
                    call: Call<RazorpayItemDataResponse?>,
                    response: Response<RazorpayItemDataResponse?>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), response.code().toString()+" Item Add Successfully..", Toast.LENGTH_SHORT).show()
                        itemName.text=""
                        itemAmount.text=""
                        itemDes.text=""
                        itemName.requestFocus()
                    }
                }

                override fun onFailure(call: Call<RazorpayItemDataResponse?>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            Toast.makeText(requireContext(), "Wait..", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<TextView>(R.id.textView4).setOnClickListener {
            startActivity(Intent(requireContext(),ShowAllItemActivity::class.java))
        }

        return view
    }

}