package com.tw.training.catkeeper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.tw.training.catkeeper.R
import kotlinx.android.synthetic.main.nearby_cat_list_item.*


class NearbyCatAdapter(val context: Context, val data: List<String>) : BaseAdapter() {
    // infalter is used to dynamically create view
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        if (convertView == null) {
            view = inflater.inflate(R.layout.nearby_cat_list_item, null, false)
        } else {
            view = convertView
        }
        var content = view.findViewById<RelativeLayout>(R.id.content)
//        content.text = data[position]
        content.setOnClickListener{
            Toast.makeText(context, data[position], Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

}