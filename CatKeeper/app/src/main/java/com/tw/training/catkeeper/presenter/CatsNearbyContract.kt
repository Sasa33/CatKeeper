package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.domain.CatsNearby


interface CatsNearbyContract {
    interface View {
        fun showNearbyCats(catsNearby: List<CatsNearby>?)
    }
    interface Presenter {
        fun start()
        fun stop()
    }
}