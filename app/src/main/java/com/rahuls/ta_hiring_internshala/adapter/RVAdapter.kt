package com.rahuls.ta_hiring_internshala.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahuls.ta_hiring_internshala.R
import com.rahuls.ta_hiring_internshala.model.Data

class RVAdapter(private var arrayList: ArrayList<Data>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private lateinit var name: ArrayList<String>
    private lateinit var date: ArrayList<String>
    private lateinit var btn: ArrayList<Boolean>
    private var size = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView = itemView.findViewById(R.id.workshopName)
        var itemDate: TextView = itemView.findViewById(R.id.workshopDate)
        var button: Button = itemView.findViewById(R.id.applyBtn)

        val prefs = itemView.context.getSharedPreferences("prefs", Context.MODE_PRIVATE)!!



        fun hideButton() {
            button.text = "Registered"
            button.isClickable = false
            button.setBackgroundColor(Color.parseColor("#FF3700B3"))
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.frame_textview, viewGroup, false)
        fetchData()
        return ViewHolder(v)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        btn[i] = viewHolder.prefs.getBoolean("registered$i", false)
        if (btn[i]) {
            viewHolder.hideButton()
            viewHolder.itemName.text = name[i]
            viewHolder.itemDate.text = date[i]
        }


    }

    override fun getItemCount(): Int {
        return size
    }

    private fun fetchData() {

        for(i in arrayList.indices){
            if(arrayList[i].button){
                name.add(arrayList[i].name)
                date.add(arrayList[i].date)
                btn.add(arrayList[i].button)
                size++
            }
        }
    }



}