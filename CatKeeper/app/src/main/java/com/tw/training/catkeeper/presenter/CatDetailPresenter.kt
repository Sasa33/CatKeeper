package com.tw.training.catkeeper.presenter

import android.util.Log
import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.network.HttpManagerFactory
import rx.Subscriber


class CatDetailPresenter(private val mDetailsView: CatDetailContract.View): CatDetailContract.Presenter{
    override fun start(catId: String) {
        // to download the details of a selected cat
        val uri = "catnip/moment/" + catId
        HttpManagerFactory.getHttpManager().getCatDetails(uri, object : Subscriber<Cat>() {
            override fun onNext(catDetails: Cat?) {
                Log.i("cat_details", "Download success!")
                mDetailsView.showCatDetails(catDetails)
            }

            override fun onError(e: Throwable?) {
                Log.i("cat_details", "Download failed!")
                mDetailsView.onDownloadFailed("download failed!")
            }

            override fun onCompleted() {
            }
        })
    }
}