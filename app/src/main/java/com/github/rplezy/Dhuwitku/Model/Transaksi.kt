package com.github.rplezy.Dhuwitku.Model

data class Transaksi(
	val code: Int? = null,
	val data: ArrayList<DataItem>? = null,
	val message: String? = null,
	val status: Boolean? = null
)
