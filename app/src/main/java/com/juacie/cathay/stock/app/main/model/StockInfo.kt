package com.juacie.cathay.stock.app.main.model

/**
 * 集合個股的資訊
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
 * @param peRatio 本益比
 * @param dividendYield 殖利率(%)
 * @param pbRatio 股票淨值比
 * @param monthlyAveragePrice 月平均價
 */
data class StockInfo(
    val code: String,
    val name: String,
    val peRatio: String? = null,
    val dividendYield: String? = null,
    val pbRatio: String? = null,
    val closingPrice: String? = null,
    val monthlyAveragePrice: String? = null,
    val tradeVolume: String? = null,
    val tradeValue: String? = null,
    val openingPrice: String? = null,
    val highestPrice: String? = null,
    val lowestPrice: String? = null,
    val change: String? = null,
    val transaction: String? = null,
)
