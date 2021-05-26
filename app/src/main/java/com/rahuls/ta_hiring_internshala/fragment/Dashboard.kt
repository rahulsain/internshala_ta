package com.rahuls.ta_hiring_internshala.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahuls.ta_hiring_internshala.R
import com.rahuls.ta_hiring_internshala.adapter.RVAdapter
import com.rahuls.ta_hiring_internshala.room.Workshop
import com.rahuls.ta_hiring_internshala.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Dashboard : Fragment() {

    private val uiScope = CoroutineScope(Dispatchers.IO)
    private var name: ArrayList<String> = ArrayList()
    private var date: ArrayList<String> = ArrayList()
    private var btn: ArrayList<Boolean> = ArrayList()
    private var size = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler_view2)
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            fetchData()
            // set the custom adapter to the RecyclerView
            adapter = RVAdapter(name,date,btn)

            adapter?.notifyDataSetChanged()
        }
    }
    private fun fetchData() {
        uiScope.launch {
            val list: List<Workshop> =
                WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao()
                    .getAll()
            for (workshop in list) {
                if (workshop.button) {
                    name.add(workshop.name)
                    date.add(workshop.date)
                    btn.add(workshop.button)
                    size++
                }
            }
        }
//        Toast.makeText(requireContext(),"stored "+(size+1)+" new workshop", Toast.LENGTH_SHORT).show()
    }
}