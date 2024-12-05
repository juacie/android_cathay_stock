package com.juacie.cathay.stock.app.main.model

import com.google.gson.annotations.SerializedName

/**
 * {domain}/exchangeReport/BWIBBU_ALL model
 *
 * 股票資訊
 * @param code 股票代號
 * @param name 股票名稱
 * @param peRatio 本益比
 * @param dividendYield 殖利率(%)
 * @param pbRatio 股票淨值比
 */
data class StockRatio(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
    @SerializedName("PEratio") val peRatio: String,
    @SerializedName("DividendYield") val dividendYield: String,
    @SerializedName("PBratio") val pbRatio: String,
)
