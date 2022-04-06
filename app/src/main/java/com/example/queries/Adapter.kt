package com.example.queries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (var recyclerArray: ArrayList<query> =ArrayList()
): RecyclerView.Adapter<Adapter.AdapterHolder>() {
    class AdapterHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvname:EditText = view.findViewById(R.id.tvname)
        var tvclass:EditText = view.findViewById(R.id.tvclass)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter.AdapterHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false)
        return AdapterHolder(itemView)

    }

    override fun onBindViewHolder(holder: Adapter.AdapterHolder, position: Int) {
        holder.tvname.setText("${recyclerArray.get(position).name}")
        holder.tvclass.setText("${recyclerArray.get(position).classname}")

//        holder.tvdate.setText("${recyclerArray.get(position).date}")


    }

    override fun getItemCount(): Int {
        return recyclerArray.size
    }


}