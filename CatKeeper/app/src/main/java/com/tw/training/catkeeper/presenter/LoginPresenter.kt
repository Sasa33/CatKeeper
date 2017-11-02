package com.tw.training.catkeeper.presenter

import android.text.TextUtils
import android.util.Log

class LoginPresenter(private val loginView: LoginContract.View): LoginContract.Presenter {
    private val TAG = "loginPresenter"

    override fun startLogin(userName: String, password: String) {

        if (validateAccount(userName, password)) {
            //TODO call login api
            Log.d(TAG, "Username: $userName, Password: $password")
            loginView.loginSuccess()
        } else {
            Log.d(TAG, "Your username or password is empty.")

        }
    }

    private fun validateAccount(userName: String, password: String): Boolean {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return false
        }
        return true
    }

}