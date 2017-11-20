package com.tw.training.catkeeper.network


class HttpManagerFactory {
    companion object {
        private val sInstance: HttpManager = HttpManagerImpl() // easy to change implement class

        fun getHttpManager(): HttpManager {
            return sInstance
        }
    }
}