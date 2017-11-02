package com.tw.training.catkeeper.presenter

interface LoginContract {
    interface Presenter {
        fun startLogin(userName: String, password: String)
    }

    interface View {
        fun loginSuccess()
    }
}

