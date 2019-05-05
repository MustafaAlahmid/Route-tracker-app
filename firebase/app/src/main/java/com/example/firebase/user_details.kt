package com.example.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_user_details.*

class user_details : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var button1: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        textView = findViewById(R.id.username)
        val us2 = intent.getStringExtra("id")
        textView.text = us2


        button = findViewById(R.id.llbtn)
        button1 = findViewById(R.id.dbtn)


        button.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java).putExtra("user",us2))
        }

        button1.setOnClickListener {
            startActivity(Intent(this,Users::class.java).putExtra("user",us2))
        }






    }
}
