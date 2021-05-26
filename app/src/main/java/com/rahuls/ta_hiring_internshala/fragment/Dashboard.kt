package com.rahuls.ta_hiring_internshala.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahuls.ta_hiring_internshala.R
import com.rahuls.ta_hiring_internshala.adapter.RVAdapter
import com.rahuls.ta_hiring_internshala.model.Data
import com.rahuls.ta_hiring_internshala.room.Workshop
import com.rahuls.ta_hiring_internshala.room.WorkshopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Dashboard : Fragment() {

    private val uiScope = CoroutineScope(Dispatchers.IO)

    private val name = arrayOf("Android Dev W1",
        "Android Dev W2", "Web Dev W1", "Learn Kotlin B1",
        "Android Dev W3", "Android Dev W4", "Web Dev W2",
        "Learn DSA")

    private val date = arrayOf("20:00 18th May, 2021", "04:00 18th May, 2021",
        "07:00 19th May, 2021", "04:00 20th May, 2021",
        "01:00 21th May, 2021", "10:00 21th May, 2021",
        "20:00 27th May, 2021", "10:00 30th May, 2021")

    private val btn = arrayOf(false, false, false, false, false, false, false, false)

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
        uiScope.launch {
            saveTask()
        }
        Toast.makeText(context, "Workshop Saved!", Toast.LENGTH_SHORT).show()
        val arrayList: ArrayList<Data> = ArrayList()
        fetchFromRoom(arrayList)
        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RVAdapter(arrayList)
        }
    }

    private suspend fun saveTask() {

//        deleteAll()

        withContext(Dispatchers.IO) {

            //creating a task
            for (i in 0 .. 7) {
                val detail = Workshop()
                detail.id = i
                detail.name = name[i]
                detail.date = date[i]
                detail.button = btn[i]
                WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao()
                    .insert(detail)
            }
        }
    }

    private fun deleteAll() {
        uiScope.launch {
            WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao().deleteAll()
        }
    }

    private fun fetchFromRoom(arrayList: ArrayList<Data>) {
        uiScope.launch {
            val list: List<Workshop> =
                WorkshopRepository.getInstance(requireContext())!!.getAppDatabase().workshopDao()
                    .getAll()
                arrayList.clear()
            for (workshop in list) {
                val data = Data()
                data.id = workshop.id
                data.name = workshop.name
                data.date = workshop.date
                data.button = workshop.button
                arrayList.add(data)
            }
        }
    }

}