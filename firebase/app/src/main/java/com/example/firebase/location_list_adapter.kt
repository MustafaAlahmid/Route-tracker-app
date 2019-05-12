package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView



class locationListAdapter (var mCtx: Context, var resorce:Int, var item: MutableList<userLoc>)
    : ArrayAdapter<userLoc>(mCtx,resorce,item){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resorce,null)
        val textView: TextView = view.findViewById(R.id.longlist)
        val textView2: TextView = view.findViewById(R.id.latelist)

        val user: userLoc = item[position]

        textView.text = user.long.toString()
        textView2.text = user.late.toString()







        return view
    }
}