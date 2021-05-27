package com.rahuls.ta_hiring_internshala.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahuls.ta_hiring_internshala.R

class RVAdapter(
    private val name: ArrayList<String>, private val date: ArrayList<String>,
    private val btn: ArrayList<Boolean>
) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView = itemView.findViewById(R.id.workshopName)
        var itemDate: TextView = itemView.findViewById(R.id.workshopDate)
        var button: Button = itemView.findViewById(R.id.applyBtn)

        fun hideButton() {
            button.text = "Registered"
            button.isClickable = false
            button.setBackgroundColor(Color.parseColor("#FF3700B3"))
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.frame_textview, viewGroup, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemName.text = name[i]
        viewHolder.itemDate.text = date[i]
        if (btn[i]) {
            viewHolder.hideButton()
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
}