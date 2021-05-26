package com.rahuls.ta_hiring_internshala.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rahuls.ta_hiring_internshala.R

class Signup : Fragment() {

    private lateinit var rEmail: EditText
    private lateinit var rConfirmPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var rUserName: EditText
    private lateinit var rPassword: EditText
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var username = ""
    private var password = ""
    private var email = ""
    private var cPassword = ""

    override fun onAttach(context: Context) {
        preferences = context.getSharedPreferences("userFile",Context.MODE_PRIVATE)
        editor = preferences.edit()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        rUserName = view.findViewById(R.id.rUsername)
        rPassword = view.findViewById(R.id.rPass)
        rEmail = view.findViewById(R.id.rEmail)
        rConfirmPassword = view.findViewById(R.id.rConPass)
        buttonRegister = view.findViewById(R.id.register)




        buttonRegister.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val msg: String

            username = rUserName.text.toString()
            password = rPassword.text.toString()
            cPassword = rConfirmPassword.text.toString()
            email = rEmail.text.toString()

            if(username.isEmpty() || email.isEmpty() || password.isEmpty() || cPassword.isEmpty()) {
                msg = "All fields are required"
            } else if(password != cPassword){
                msg = "Password does not match"
            } else if(password.length < 7){
                msg = "Password too short"
            } else {

                editor.putString("userName", username)
                editor.putString("password", password)
                editor.apply()

                msg = "Registered"

                activity.supportFragmentManager.popBackStack()
            }
            Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

    }
}