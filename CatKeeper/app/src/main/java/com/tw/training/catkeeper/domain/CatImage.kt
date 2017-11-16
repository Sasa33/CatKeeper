package com.tw.training.catkeeper.domain

import com.google.gson.annotations.SerializedName


data class CatImage(@SerializedName("image")val imageUrl: String)