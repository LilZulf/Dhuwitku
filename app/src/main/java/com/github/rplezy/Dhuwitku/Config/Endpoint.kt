package com.github.rplezy.Dhuwitku.Config

import com.github.rplezy.Dhuwitku.Model.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Endpoint {
    @POST("auth/login")
    @FormUrlEncoded
    fun doLogin(@Field("email") email: String,
                @Field("pass") password: String
    ): Call<UserModel>
    @POST("auth/register")
    @FormUrlEncoded
    fun doRegister(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("pass") password: String
    ): Call<UserModel>
    @POST("auth/getUserById")
    @FormUrlEncoded
    fun getById(
        @Field("idUser") name: String
    ): Call<UserModel>
}