package com.example.capstoneproject.datajdhin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R

class AdapterJdhin(private var list: ArrayList<JdhinSerialized>): RecyclerView.Adapter<AdapterJdhin.JdhinViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setterList(users: ArrayList<JdhinSerialized>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: JdhinSerialized)
    }


    inner class JdhinViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView = itemView.findViewById(R.id.tv_title)
        var number: TextView = itemView.findViewById(R.id.numberRegulation)
        var status: TextView = itemView.findViewById(R.id.statusRegulation)
        var subject: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JdhinViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_data2, parent, false)
        return JdhinViewHolder(view)
    }

    override fun onBindViewHolder(holder: JdhinViewHolder, position: Int) {
        val data = list[position]
        holder.tvName.text = data.name
        holder.subject.text = data.url
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(list[holder.adapterPosition] )}
    }

    override fun getItemCount(): Int {
        return list.size
    }
}