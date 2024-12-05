package com.juacie.cathay.stock.app.main.model

import com.google.gson.annotations.SerializedName

/**
 * {domain}/exchangeReport/STOCK_DAY_AVG_ALL model
 *
 * 股票資訊
 * @param code 股票代號
 * @param name 股票名稱
 * @param closingPrice 收盤價
 * @param monthlyAveragePrice 月平均價
 */
data class StockDayAverage(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
    @SerializedName("ClosingPrice") val closingPrice: String,
    @SerializedName("MonthlyAveragePrice") val monthlyAveragePrice: String,
)
