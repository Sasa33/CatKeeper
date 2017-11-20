package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import rx.Subscriber


interface HttpManager {
    fun getCat(callback: Subscriber<GetNearbyCatResponse>)
}