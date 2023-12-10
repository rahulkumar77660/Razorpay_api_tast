package com.example.razorpayapitast

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(),JsonInterfaceDelteUpdate{
    lateinit var myAdapter: JsonAdapter
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val jsonApi = retrofit.create(JsonholderApi::class.java)
        val myCall = jsonApi.getData()
        myCall.enqueue(object : Callback<List<JsonplaceholderDataModeForGetItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<JsonplaceholderDataModeForGetItem>?>,
                response: Response<List<JsonplaceholderDataModeForGetItem>?>
            ) {
                myAdapter = JsonAdapter(requireContext(),response.body()!!,this@HomeFragment)
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter=myAdapter
            }

            override fun onFailure(
                call: Call<List<JsonplaceholderDataModeForGetItem>?>,
                t: Throwable
            ) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }

    override fun delte(id: Int, position: Int) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val jsonApi = retrofit.create(JsonholderApi::class.java)
        jsonApi.deleteData(id).enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
               if (response.isSuccessful){
                   Toast.makeText(requireContext(), response.code().toString(), Toast.LENGTH_SHORT).show()
               }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun update(id: Int, title: String, body: String) {

        val intent = Intent(context,JsonUpdateActivity::class.java)
        intent.putExtra("id",id.toString())
        intent.putExtra("title",title.toString())
        intent.putExtra("body",body.toString())
        startActivity(intent)
    }

//    override fun update(id: Int, position: Int, title: String, body: String) {
//        val intent = Intent(requireContext(),JsonUpdateActivity::class.java)
//        intent.putExtra("id",id.toString())
//        intent.putExtra("title",title.toString())
//        intent.putExtra("body",body.toString())
//        startActivity(intent)
//    }


}