package com.example.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Users : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var btn:Button
    lateinit var ref:DatabaseReference
    lateinit var usersList:MutableList<Users1>
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)


        usersList = mutableListOf()
        listView = findViewById(R.id.list)
        ref = FirebaseDatabase.getInstance().getReference("users")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    usersList.clear()
                    for (e in p0.children){
                        val user = e.getValue(Users1::class.java)
                        usersList.add(user!!)

                    }
                    val adapter = MyAdapterList(this@Users,R.layout.my_list_item, usersList)
                    listView.adapter = adapter
                }
            }

        })

        //////////////////////////////
        btn = findViewById(R.id.lobtn)

        //making the sign out function
        btn.setOnClickListener {

            Toast.makeText(this, "logging out ...", Toast.LENGTH_LONG).show()
            myAuth.signOut()

            myAuth.addAuthStateListener {
                if (myAuth.currentUser == null) {
                    this.finish()
                }


            }
        }

    }
}
