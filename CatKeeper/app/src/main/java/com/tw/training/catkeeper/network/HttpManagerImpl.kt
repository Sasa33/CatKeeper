package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class HttpManagerImpl: HttpManager {
    override fun getCatDetails(url: String, callback: Subscriber<Cat>) {
        CatService.getCatService().getCatDetails(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
    }

    override fun getCat(callback: Subscriber<GetNearbyCatResponse>) {
        CatService.getCatService().getNearbyCat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
    }
}