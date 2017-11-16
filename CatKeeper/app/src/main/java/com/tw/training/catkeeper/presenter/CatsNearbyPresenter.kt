package com.tw.training.catkeeper.presenter

import android.graphics.Bitmap
import android.os.AsyncTask
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.utils.HttpUtils
import org.json.JSONObject


class CatsNearbyPresenter(private val mCatsNearbyView: CatsNearbyContract.View): CatsNearbyContract.Presenter {
//    private var mCatsNearbyTask: CatsNearbyAsyncTask?

    override fun start() {
        loadingCatsNearbyList()
    }

    override fun stop() {

    }

    private fun loadingCatsNearbyList() {
        val mCatsNearbyTask = CatsNearbyAsyncTask()
        mCatsNearbyTask.execute("http://10.0.2.2:8080/catnip/moment/")
    }

    private val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    inner class CatsNearbyAsyncTask: AsyncTask<String, Unit, List<CatsNearby>?>() {
        override fun doInBackground(vararg params: String?): List<CatsNearby>? {
            val response = HttpUtils().doGet(params[0]!!)
            val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
            return gson.fromJson(JSONObject(response).getString("moments"),
                    object: TypeToken<List<CatsNearby>>(){}.type)
        }

        override fun onPostExecute(result: List<CatsNearby>?) {
            super.onPostExecute(result)
            mCatsNearbyView.showNearbyCats(result)
        }
    }
}