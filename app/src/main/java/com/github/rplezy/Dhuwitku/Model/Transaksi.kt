package com.github.rplezy.Dhuwitku.Model

data class Transaksi(
    val code: Int? = null,
    val data: ArrayList<ItemLogTransaksi>? = null,
    val message: String? = null,
    val status: Boolean? = null
)
