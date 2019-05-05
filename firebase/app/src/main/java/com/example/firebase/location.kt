package com.example.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class location : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var usersList:MutableList<ll>
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val name = intent.getStringExtra("name")
        val time  =intent.getStringExtra("time")

        listView = findViewById(R.id.listw)
        usersList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("$name's locations").child(time)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    usersList.clear()
                    for (e in p0.children){
                        val user = e.getValue(ll::class.java)
                        usersList.add(user!!)

                    }
                    val adapter = locationListAdapter(this@location,R.layout.locationlist,usersList)
                    listView.adapter = adapter

                }
            }

        })



    }
}
