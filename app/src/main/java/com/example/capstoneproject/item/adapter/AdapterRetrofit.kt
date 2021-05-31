package com.example.capstoneproject.item.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.item.serialized.SubjectSerialized

class AdapterRetrofit(private var list: ArrayList<SubjectSerialized>): RecyclerView.Adapter<AdapterRetrofit.RetrofitViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setterList(users: ArrayList<SubjectSerialized>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: SubjectSerialized)
    }

    inner class RetrofitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RetrofitViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_data, viewGroup, false)

        return RetrofitViewHolder(view)
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {
        val github = list[position]
        holder.tvName.text = github.name
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(list[holder.adapterPosition])}
    }

    override fun getItemCount(): Int {
        return list.size
    }
}