package com.rahuls.ta_hiring_internshala.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.rahuls.ta_hiring_internshala.R
import com.rahuls.ta_hiring_internshala.fragment.AvailableWorkshops

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<AvailableWorkshops>(R.id.fragment_available_workshops)
            }
        }

    }
}