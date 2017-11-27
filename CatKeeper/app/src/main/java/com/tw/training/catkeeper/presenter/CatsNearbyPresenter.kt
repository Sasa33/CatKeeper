package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import com.tw.training.catkeeper.network.HttpManagerFactory
import rx.Subscriber


class CatsNearbyPresenter(private val mCatsNearbyView: CatsNearbyContract.View): CatsNearbyContract.Presenter {
    override fun start() {
        HttpManagerFactory.getHttpManager().getCat(object : Subscriber<GetNearbyCatResponse>() {
            override fun onError(e: Throwable?) {
                mCatsNearbyView.onGetDataFail(e.toString())
            }

            override fun onNext(response: GetNearbyCatResponse?) {
                mCatsNearbyView.onGetDataSucceed(response!!.moments)
            }

            override fun onCompleted() {

            }

        })
    }

    override fun stop() {

    }
}