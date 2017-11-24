package com.tw.training.catkeeper.adapter

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import com.tw.training.catkeeper.presenter.CatsNearbyPresenter
import com.tw.training.catkeeper.utils.HttpUtils


class NearbyCatAdapter(val context: Context, var data: List<GetNearbyCatResponse.MomentsBean>?) : BaseAdapter() {
    private lateinit var avatar: ImageView
    private lateinit var thumbImageView: ImageView
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

//        val containers = arrayOf(R.id.image1, R.id.image2, R.id.image3)
//        currentData.thumbs!!.forEachIndexed { index, thumb ->
//            thumbImageView = view.findViewById<ImageView>(containers[index])
//            Glide.with(context).load(baseUrl + thumb.image).into(thumbImageView)
//        }

        currentData.thumbs!!.forEach { thumb ->
            var params = ViewGroup.LayoutParams(200, 200)
            var imageView = ImageView(context)
            thumbContainer.addView(imageView, params)
            Glide.with(context).load(baseUrl + thumb.image).into(imageView)
        }

        return view
    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var view: View
//        if (convertView == null) {
//            view = inflater.inflate(R.layout.nearby_cat_list_item, null, false)
//        } else {
//            view = convertView
//        }
//        val content = view.findViewById<RelativeLayout>(R.id.content)
//        val name = view.findViewById<TextView>(R.id.name)
//        val description = view.findViewById<TextView>(R.id.description)
//        val timestamp = view.findViewById<TextView>(R.id.timestamp)
//        avatar = view.findViewById<ImageView>(R.id.avatar)
//
//        val currentData = data!![position]
//
//        downloadImage(currentData.avatar.imageUrl, avatar)
//
//        name.text = currentData.name
//        timestamp.text = currentData.updateTime.time.toString()
//        description.text = currentData.description
//
//        val containers = arrayOf(R.id.image1, R.id.image2, R.id.image3)
//        currentData.thumbsList.forEachIndexed { index, value ->
//            thumbImage1 = view.findViewById<ImageView>(containers[index])
//            downloadImage(value.imageUrl, thumbImage1)
//        }
//
//        content.setOnClickListener{
////            Toast.makeText(context, data[position], Toast.LENGTH_SHORT).show()
//        }
//        return view
//    }

    override fun getItem(position: Int): Any {
        return data!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data!!.size
    }

    private fun downloadImage(imageUrl: String, container: ImageView) {
        val baseUrl = "http://10.0.2.2:8080/catnip"
        DownloadImageAsyncTask().execute(Pair(baseUrl + imageUrl, container))
    }

    inner class DownloadImageAsyncTask: AsyncTask<Pair<String, ImageView>, Unit, Pair<Bitmap, ImageView>?>() {
        override fun doInBackground(vararg params: Pair<String, ImageView>?): Pair<Bitmap, ImageView>? {
            val downloadedImage = HttpUtils().doDownloadImage(params[0]!!.first)
            val container1 = params[0]!!.second
            return Pair(downloadedImage!!, container1)
        }

        override fun onPostExecute(result: Pair<Bitmap, ImageView>?) {
            super.onPostExecute(result)
            result!!.second.setImageBitmap(result.first)
        }
    }
}