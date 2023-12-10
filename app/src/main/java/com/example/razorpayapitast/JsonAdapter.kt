package com.example.razorpayapitast

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.http.Body

interface JsonInterfaceDelteUpdate{

    fun delte(id : Int,position: Int)
    fun update(id: Int,title:String,body: String)
}

class JsonAdapter(val context: Context,val listItem : List<JsonplaceholderDataModeForGetItem>,val listener : JsonInterfaceDelteUpdate) : RecyclerView.Adapter<JsonAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title)
        val body : TextView = itemView.findViewById(R.id.body)
        val id : TextView = itemView.findViewById(R.id.id)
        val moreHori: ImageView=itemView.findViewById(R.id.moreHori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_desing_for_json,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p0 = listItem[position]
        holder.id.text=p0.id.toString()
        holder.body.text=p0.body.toString()
        holder.title.text=p0.title.toString()
        
        holder.moreHori.setOnClickListener {
            val pupupMenu = PopupMenu(context,holder.moreHori)
            pupupMenu.inflate(R.menu.delet_update_menu)

            pupupMenu.setOnMenuItemClickListener{
                when(it.itemId){
                    R.id.updateBtn->{
                        listener.update(p0.id,p0.title,p0.body)
                    }
                    R.id.delteBtn->{
                        listener.delte(p0.id,position)
                    }
                }
                true
            }
            pupupMenu.show()
        }
    }

}