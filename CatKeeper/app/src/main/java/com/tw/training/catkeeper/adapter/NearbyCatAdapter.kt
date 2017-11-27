package com.tw.training.catkeeper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.domain.GetNearbyCatResponse


class NearbyCatAdapter(val context: Context, var data: List<GetNearbyCatResponse.MomentsBean>?) : BaseAdapter() {
    private lateinit var avatar: ImageView
    // infalter is used to dynamically create view
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        if (convertView == null) {
            view = inflater.inflate(R.layout.nearby_cat_list_item, null, false)
        } else {
            view = convertView
        }
        val content = view.findViewById<RelativeLayout>(R.id.content)
        val name = view.findViewById<TextView>(R.id.name)
        val description = view.findViewById<TextView>(R.id.description)
        val timestamp = view.findViewById<TextView>(R.id.timestamp)
        val thumbContainer = view.findViewById<LinearLayout>(R.id.thumbContainer)
        avatar = view.findViewById<ImageView>(R.id.avatar)

        val currentData = data!![position]
        name.text = currentData.cat
        description.text = currentData.message
        timestamp.text = currentData.timestamp

        val baseUrl = "http://10.0.2.2:8080/catnip"
        Glide.with(context).load(baseUrl + currentData.avatar!!.image).into(avatar)

        currentData.thumbs!!.forEach { thumb ->
            var params = RelativeLayout.LayoutParams(250, 250)
            params.setMargins(0, 0, 25, 0)
            var imageView = ImageView(context)
            thumbContainer.addView(imageView, params)
            Glide.with(context).load(baseUrl + thumb.image).into(imageView)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return data!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data!!.size
    }
}