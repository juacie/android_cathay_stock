package com.juacie.cathay.stock.app.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juacie.cathay.stock.app.base.BaseViewModel
import com.juacie.cathay.stock.app.kit.function.LogObj
import com.juacie.cathay.stock.app.kit.network.BaseResponse
import com.juacie.cathay.stock.app.kit.network.Client
import com.juacie.cathay.stock.app.main.model.StockDay
import com.juacie.cathay.stock.app.main.model.StockDayAverage
import com.juacie.cathay.stock.app.main.model.StockInfo
import com.juacie.cathay.stock.app.main.model.StockRatio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class StockViewModel : BaseViewModel() {
    private val _stockInfoList = MutableLiveData<List<StockInfo>>()
    open val stockInfoList: LiveData<List<StockInfo>> = _stockInfoList

    private val _filteredStockInfoList = MutableLiveData<List<StockInfo>>()
    val filteredStockInfoList: LiveData<List<StockInfo>> = _filteredStockInfoList

    // 新增變數來記錄排序狀態
    private var isAscending = false // 默認是降序

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        viewModelScope.launch {
            _searchQuery.collect { query ->
                _filteredStockInfoList.value = _stockInfoList.value.orEmpty()
                    .filter { it.code.startsWith(query, ignoreCase = true) }
            }
        }
    }

    /**
     * 獲得股票基本資訊
     */
    fun fetchStockInfo() {
        viewModelScope.launch {
            showLoadingView(true)
            val stockInfos = getStockInfoList()
            LogObj.trace("Fetched data: $stockInfos")
            // 根據排序狀態進行排序
            val sortedStockInfos = if (isAscending) {
                stockInfos.sortedBy { it.code } // 升序排列
            } else {
                stockInfos.sortedByDescending { it.code } // 降序排列
            }
            _filteredStockInfoList.postValue(sortedStockInfos) // 初始化過濾清單
            _stockInfoList.postValue(sortedStockInfos)
            showLoadingView(false) // 確保刷新狀態結束
        }
    }

    /**
     * 排序方法
     */
    fun sortStockInfoList(ascending: Boolean) {
        // 更新排序狀態
        isAscending = ascending
        // 當用戶選擇排序後，重新調用 fetchStockInfo 來應用新的排序
        fetchStockInfo()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private suspend fun getStockInfoList(): List<StockInfo> {
        val stockRatios = getStockRatioList()
        val stockDayAverages = getStockDayAverageList()
        val stockDays = getStockDayList()

        val combinedMap = mutableMapOf<String, StockInfo>()

        // 合併 StockRatio
        stockRatios.forEach {
            combinedMap[it.code] = StockInfo(
                code = it.code,
                name = it.name,
                peRatio = it.peRatio,
                dividendYield = it.dividendYield,
                pbRatio = it.pbRatio,
                closingPrice = null,
                monthlyAveragePrice = null,
                tradeVolume = null,
                tradeValue = null,
                openingPrice = null,
                highestPrice = null,
                lowestPrice = null,
                change = null,
                transaction = null
            )
        }

        // 合併 StockDayAverage
        stockDayAverages.forEach {
            val existing = combinedMap[it.code]
            combinedMap[it.code] = (existing ?: StockInfo(code = it.code, name = it.name)).copy(
                closingPrice = it.closingPrice,
                monthlyAveragePrice = it.monthlyAveragePrice
            )
        }

        // 合併 StockDay
        stockDays.forEach {
            val existing = combinedMap[it.code]
            combinedMap[it.code] = (existing ?: StockInfo(code = it.code, name = it.name)).copy(
                tradeVolume = it.tradeVolume,
                tradeValue = it.tradeValue,
                openingPrice = it.openingPrice,
                highestPrice = it.highestPrice,
                lowestPrice = it.lowestPrice,
                change = it.change,
                transaction = it.transaction
            )
        }

        return combinedMap.values.toList()
    }

    private suspend fun getStockRatioList(): List<StockRatio> {
        val tag = "getFavoritePoolsIdList"
        LogObj.trace("start $tag")

        return withContext(Dispatchers.IO) {
            val resp = Client.safeApiCall {
                Client.apiService.svGetStockRatioList()
            }

            val result = when (resp) {
                is BaseResponse.Success<List<StockRatio>> -> {
                    LogObj.trace("$tag resp = $resp")
                    resp.data
                }

                is BaseResponse.Error -> {
                    LogObj.trace("$tag error = ${resp.error}")
                    emptyList()
                }
            }

            LogObj.trace("end $tag")
            result
        }
    }

    private suspend fun getStockDayAverageList(): List<StockDayAverage> {
        val tag = "getStockDayAverageList"
        LogObj.trace("start $tag")

        return withContext(Dispatchers.IO) {
            val resp = Client.safeApiCall {
                Client.apiService.svGetStockDayAverageList()
            }

            val result = when (resp) {
                is BaseResponse.Success<List<StockDayAverage>> -> {
                    LogObj.trace("$tag resp = $resp")
                    resp.data
                }

                is BaseResponse.Error -> {
                    LogObj.trace("$tag error = ${resp.error}")
                    emptyList()
                }
            }

            LogObj.trace("end $tag")
            result
        }
    }

    private suspend fun getStockDayList(): List<StockDay> {
        val tag = "getStockDayList"
        LogObj.trace("start $tag")

        return withContext(Dispatchers.IO) {
            val resp = Client.safeApiCall {
                Client.apiService.svGetStockDayList()
            }

            val result = when (resp) {
                is BaseResponse.Success<List<StockDay>> -> {
                    LogObj.trace("$tag resp = $resp")
                    resp.data
                }

                is BaseResponse.Error -> {
                    LogObj.trace("$tag error = ${resp.error}")
                    emptyList()
                }
            }

            LogObj.trace("end $tag")
            result
        }
    }
}