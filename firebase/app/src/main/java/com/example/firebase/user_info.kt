package com.example.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatViewInflater
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class user_info : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var usersList:MutableList<uls>
    lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val name = intent.getStringExtra("id")

        listView = findViewById(R.id.locationslistView)
        usersList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("$name's locations")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    usersList.clear()
                    for (e in p0.children){
                        var user = e.getValue(uls::class.java)
                        usersList.add(user!!)

                    }
                    val adapter = userLocationAdapter(this@user_info,R.layout.user_location_adapter,usersList)
                    listView.adapter = adapter

                }
            }

        })
        listView.setOnItemClickListener { parent, view, position, id ->




            val time = usersList.get(position).time


            startActivity(Intent(this,location::class.java).putExtra("time",time).putExtra("name",name))

        }



    }
}
