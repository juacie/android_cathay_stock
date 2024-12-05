package com.juacie.cathay.stock.app.main.model

import com.google.gson.annotations.SerializedName

/**
 * {domain}/exchangeReport/STOCK_DAY_ALL model
 *
 * 股票資訊
 * @param code 股票代號
 * @param name 股票名稱
 * @param tradeVolume 成交股數
 * @param tradeValue 成交金額
 * @param openingPrice 開盤價
 * @param highestPrice 最高價
 * @param lowestPrice 最低價
 * @param closingPrice 收盤價
 * @param change 漲跌價差
 * @param transaction 成交筆數
 */
data class StockDay(
    @SerializedName("Code") val code: String,
    @SerializedName("Name") val name: String,
    @SerializedName("TradeVolume") val tradeVolume: String,
    @SerializedName("TradeValue") val tradeValue: String,
    @SerializedName("OpeningPrice") val openingPrice: String,
    @SerializedName("HighestPrice") val highestPrice: String,
    @SerializedName("LowestPrice") val lowestPrice: String,
    @SerializedName("ClosingPrice") val closingPrice: String,
    @SerializedName("Change") val change: String,
    @SerializedName("Transaction") val transaction: String,
)
