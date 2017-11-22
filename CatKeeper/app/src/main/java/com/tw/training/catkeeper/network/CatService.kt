package com.tw.training.catkeeper.network

import com.tw.training.catkeeper.domain.Cat
import com.tw.training.catkeeper.domain.GetNearbyCatResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import rx.Observable


interface CatService {
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