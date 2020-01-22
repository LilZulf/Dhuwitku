package com.github.rplezy.Dhuwitku.Config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    private  var endpoint : Endpoint ? = null

    //private val BASE_URL = "http://{your host}/api-skeleton/dhuwitkuBeta1/"
    private val BASE_URL = "http://192.168.43.238/api-skeleton/dhuwitkuBeta1/"

    fun get(): Endpoint {
        if (endpoint == null){
            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            endpoint = retrofit.create(Endpoint::class.java)
        }
        return endpoint!!
    }
}