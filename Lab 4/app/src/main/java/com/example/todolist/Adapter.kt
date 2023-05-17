package com.example.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.db.Task


class Adapter(var data: List<Task>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val description: TextView

        init {
            name = itemView.findViewById(R.id.task_name_card)
            description = itemView.findViewById(R.id.task_description_card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = data[position].name
        val description = data[position].description

        if (name.length > 15) {
            val nameShorted = name.subSequence(0, 15).toString() + "..."
            holder.name.text = nameShorted
        } else {
            holder.name.text = name
        }

        if (description.length > 70) {
            val descriptionShorted = description.subSequence(0, 70).toString() + "..."
            holder.description.text = descriptionShorted
        } else {
            holder.description.text = description
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MoreInfoActivity::class.java)
            intent.putExtra("id", data[position].id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}