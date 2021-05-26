package com.rahuls.ta_hiring_internshala.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahuls.ta_hiring_internshala.R
import com.rahuls.ta_hiring_internshala.adapter.RecyclerViewAdapter
import com.rahuls.ta_hiring_internshala.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AvailableWorkshops : Fragment() {

    private val uiScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_workshops, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler_view1)
        uiScope.launch {
            WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao().deleteAll()
        }
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RecyclerViewAdapter()
        }
    }
}