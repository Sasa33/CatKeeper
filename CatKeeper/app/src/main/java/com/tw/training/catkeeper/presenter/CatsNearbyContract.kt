package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.domain.GetNearbyCatResponse


interface CatsNearbyContract {
    interface View {
        fun showNearbyCats(catsNearby: List<CatsNearby>?)
        fun onGetDataSucceed(data: List<GetNearbyCatResponse.MomentsBean>?)
        fun onGetDataFail(msg: String)
    }
    interface Presenter {
        fun start()
        fun stop()
    }
}