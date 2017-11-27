package com.tw.training.catkeeper.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.stream.JsonWriter
import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import com.tw.training.catkeeper.domain.LoginCredential
import com.tw.training.catkeeper.domain.LoginKey
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class HttpManagerImpl: HttpManager {
    override fun login(username: String, password: String, callback: Subscriber<LoginKey>) {
        CatService.getCatService().login(LoginCredential(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
    }

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