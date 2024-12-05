package com.juacie.cathay.stock.app.kit.network

import com.juacie.cathay.stock.app.main.model.StockDay
import com.juacie.cathay.stock.app.main.model.StockDayAverage
import com.juacie.cathay.stock.app.main.model.StockRatio
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    //==============================================================================================
    //region exchangeReport(交易所報告資訊)

    /**
     * 上市個股日本益比、殖利率及股價淨值比
     */
    @GET("/v1/exchangeReport/BWIBBU_ALL")
    suspend fun svGetStockRatioList(): Response<List<StockRatio>>

    /**
     * 上市個股日收盤價即月平均價
     */
    @GET("/v1/exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun svGetStockDayAverageList(): Response<List<StockDayAverage>>

    /**
     * 上市個股日成交資訊
     */
    @GET("/v1/exchangeReport/STOCK_DAY_ALL")
    suspend fun svGetStockDayList(): Response<List<StockDay>>
    //endregion
    //==============================================================================================

}