package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import com.tw.training.catkeeper.domain.LoginCredential
import com.tw.training.catkeeper.domain.LoginKey
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable


interface CatService {
    @POST("catnip/login/")
    fun login(@Body loginCredential: LoginCredential): Observable<LoginKey>

    @GET("catnip/moment/")
    fun getNearbyCat(): Observable<GetNearbyCatResponse>

    @GET
    fun getCatDetails(@Url url: String): Observable<Cat>

    companion object { // static object
        fun getCatService(): CatService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // because return type is Observable
                    .addConverterFactory(GsonConverterFactory.create()) // use Gson to convert json to response
//                    .client()   // default is OK http
                    .baseUrl("http://10.0.2.2:8080")
                    .build()
            return retrofit.create(CatService::class.java)
        }
    }
}