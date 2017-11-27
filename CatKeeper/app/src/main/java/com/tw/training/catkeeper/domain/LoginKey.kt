package com.tw.training.catkeeper.domain

import com.google.gson.annotations.SerializedName


data class LoginKey(@SerializedName("key")val key: String) {
}