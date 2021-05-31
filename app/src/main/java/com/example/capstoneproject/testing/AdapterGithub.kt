package com.example.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.github.ui.resource.Github

class AdapterGithub (private val listGithub: ArrayList<Github>) : RecyclerView.Adapter<AdapterGithub.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Github)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_data, viewGroup, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val github = listGithub[position]
        holder.tvName.text = github.name
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGithub[holder.adapterPosition])}
    }

    override fun getItemCount(): Int {
        return listGithub.size
    }
}