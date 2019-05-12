package com.example.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class location : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var usersList1:MutableList<userLoc>
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val name = intent.getStringExtra("name")
        val time  =intent.getStringExtra("time").toString()

        listView = findViewById(R.id.listw)
        usersList1 = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("$name's locations").child(time)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    usersList1.clear()
                    for (e in p0.children){
                        val user = e.getValue(userLoc::class.java)
                        usersList1.add(user!!)

                    }
                    val adapter = locationListAdapter(this@location,R.layout.locationlist,usersList1)
                    listView.adapter = adapter

                }
            }

        })



    }
}
