package com.tw.training.catkeeper.presenter

import android.text.TextUtils
import android.util.Log
import com.tw.training.catkeeper.domain.LoginKey
import com.tw.training.catkeeper.network.HttpManagerFactory
import rx.Subscriber

class LoginPresenter(private val loginView: LoginContract.View): LoginContract.Presenter {
    private val TAG = "loginPresenter"

    override fun startLogin(userName: String, password: String) {

        if (validateAccount(userName, password)) {
            Log.i(TAG, "Username: $userName, Password: $password")

            HttpManagerFactory.getHttpManager().login(userName, password, object: Subscriber<LoginKey>() {
                override fun onCompleted() {
                    Log.i(TAG, "api call finished")
                }

                override fun onNext(loginKey: LoginKey?) {
                    Log.i(TAG, "api call succeed")
                    Log.i(TAG, loginKey.toString())
                    loginView.loginSuccess()
                }

                override fun onError(e: Throwable?) {
                    loginView.loginFailed()
                    Log.i(TAG, "api call failed")
                }

            })


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