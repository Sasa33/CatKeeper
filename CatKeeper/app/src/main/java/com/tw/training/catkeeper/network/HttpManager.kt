package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import com.tw.training.catkeeper.domain.LoginKey
import rx.Subscriber


interface HttpManager {
    fun getCat(callback: Subscriber<GetNearbyCatResponse>)
    fun getCatDetails(url: String, callback: Subscriber<Cat>)
    fun login(username: String, password: String, callback: Subscriber<LoginKey>)
}