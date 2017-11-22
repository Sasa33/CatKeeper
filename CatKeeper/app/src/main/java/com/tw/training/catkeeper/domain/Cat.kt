package com.tw.training.catkeeper.domain

import com.google.gson.annotations.SerializedName


data class Cat(@SerializedName("cat")val cat: String,
               @SerializedName("message")val message: String,
               @SerializedName("banner")val banner: CatImage,
               @SerializedName("avatar")val avatar: CatImage,
               @SerializedName("age")val age: Int,
               @SerializedName("kind")val kind: String,
               @SerializedName("stars")val stars: String,
               @SerializedName("follows")val follows: String,
               @SerializedName("fishes")val fishes: String,
               @SerializedName("adopted")val adopted: String) {
}