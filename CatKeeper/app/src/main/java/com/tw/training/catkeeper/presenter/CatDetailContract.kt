package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.domain.Cat


interface CatDetailContract {
    interface Presenter {
        fun start(catId: String)
    }

    interface View {
        fun showCatDetails(cat: Cat?)
        fun onDownloadFailed(msg: String)
    }
}