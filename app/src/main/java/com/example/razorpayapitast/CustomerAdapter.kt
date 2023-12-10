package com.example.razorpayapitast

import android.content.Context
import android.content.Intent
import android.text.style.UpdateAppearance
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

interface CustomerInterListener{

    fun update(id : String,position: Int,name:String,email:String,contact:String)
}

class CustomerAdapter(val context : Context,val listItem : List<ItemX>,val listener: CustomerInterListener) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cName : TextView = itemView.findViewById(R.id.cusName)
        val cEmail : TextView = itemView.findViewById(R.id.cusEmail)
        val cContact: TextView = itemView.findViewById(R.id.cusContact)
        val update : ImageView = itemView.findViewById(R.id.customerUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_row_design,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p0 = listItem[position]
        holder.cName.text=p0.name.toString()
        holder.cEmail.text=p0.email
        holder.cContact.text=p0.contact
        holder.update.setOnClickListener {
            listener.update(p0.id.toString(),position,p0.name.toString(),p0.email.toString(),p0.contact.toString())
            Toast.makeText(context, "Wait...", Toast.LENGTH_SHORT).show()
        }
    }
}