package com.github.rplezy.Dhuwitku.Config

//import com.github.rplezy.Dhuwitku.Model.AddTransaksi
import com.github.rplezy.Dhuwitku.Model.*
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
    @POST("saldo/topup")
    @FormUrlEncoded
    fun doTopUp(
        @Field ("idUser") id_user : String,
        @Field("nominal") nominal : String
    ):Call<UserModel>
    @POST("saldo/gift")
    @FormUrlEncoded
    fun doGift(
        @Field ("idUser") id_user : String,
        @Field("nominal") nominal : String,
        @Field("idTujuan") id_tujuan : String,
        @Field("title") title:String,
        @Field("pesan") pesan : String
    ):Call<UserModel>
    @POST("transaksi/semua")
    @FormUrlEncoded
    fun getTransaksi(
        @Field( "idTransaksi") id_transaksi : String
    ):Call<Transaksi>
    @POST("category/semua")
    @FormUrlEncoded
    fun getCategory(
        @Field( "idUser") id_user : String
    ):Call<Category>
    @POST("category/addKategori")
    @FormUrlEncoded
    fun addCategory(
        @Field( "idUser") id_user : String,
        @Field("namaKategori") namaKategori: String
    ):Call<Category>
    @POST("transaksi/addlog")
    @FormUrlEncoded
    fun tambahTransaksi(
        @Field("idTransaksi") id_transaksi: String,
        @Field("idCategory") id_category: String,
        @Field( "deskripsi") desc: String,
        @Field( "jumlah") jumlah_transaksi: String,
        @Field( "state") state: String
    ):Call<AddTransaksi>
    @POST("transaksi/gettransaksi")
    @FormUrlEncoded
    fun getMainTransaksi(
        @Field( "idUser") id_user : String,
        @Field("tanggal") tanggal : String
    ):Call<MainTransaksi>
    @POST("transaksi/addtransaksi")
    @FormUrlEncoded
    fun addMainTransaksi(
        @Field( "idUser") id_user : String
    ):Call<Transaksi>
    @POST("transaksi/laporan")
    @FormUrlEncoded
    fun getLaporan(
        @Field("idUser") id_user: String,
        @Field("tanggal") tanggal: String,
        @Field("kind") kind : String
    ):Call<Basic>
    @POST("transaksi/laporantgl")
    @FormUrlEncoded
    fun getLaporanTgl(
        @Field("idUser") id_user: String,
        @Field("awal") awal: String,
        @Field("akhir") akhir :String,
        @Field("kind") kind : String
    ):Call<Basic>
    @POST("saldo/riwayat")
    @FormUrlEncoded
    fun getHistory(
        @Field("idUser") id_user : String,
        @Field("kind") type : String
    ):Call<History>
    @POST("transaksi/deleteLog")
    @FormUrlEncoded
    fun deleteLog(
        @Field("idLog")id_log: String
    ):Call<Basic>
    @POST("transaksi/riwayat")
    @FormUrlEncoded
    fun riwayat(
        @Field("idUser") id_user: String
    ):Call<Riwayat>

}