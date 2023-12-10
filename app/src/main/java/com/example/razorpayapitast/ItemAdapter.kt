package com.example.razorpayapitast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface RazorpayItemInteface{

    fun onDelte(id: String,position: Int)
    fun onUpdate(id:String,item: String,des:String,price:Int,position: Int)
}

class ItemAdapter(val context: Context,val listItem: List<Item>,val listener:RazorpayItemInteface) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val item : TextView = itemView.findViewById(R.id.itemNameShow)
        val des : TextView = itemView.findViewById(R.id.itemDesShow)
        val price : TextView = itemView.findViewById(R.id.itemPriceShow)
        val delete : ImageView = itemView.findViewById(R.id.itemImageView_delete)
        val update : ImageView = itemView.findViewById(R.id.itemImageView_update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_desing,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p0 = listItem[position]
        holder.item.text=p0.name.toString()
        holder.des.text=p0.description.toString()
        holder.price.text=p0.amount.toString()

        holder.delete.setOnClickListener {
            listener.onDelte(p0.id,position)
        }
        holder.update.setOnClickListener {
            listener.onUpdate(p0.id,p0.name.toString(),p0.description,p0.amount.toString().toInt(),position)
        }
    }
}