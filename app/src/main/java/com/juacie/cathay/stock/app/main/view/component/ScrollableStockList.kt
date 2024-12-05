package com.juacie.cathay.stock.app.main.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.juacie.cathay.stock.app.main.viewModel.StockViewModel
import com.juacie.cathay.stock.ui.theme.Typography

@Composable
internal fun ScrollableStockList(viewModel: StockViewModel) {
    val stockInfoList by viewModel.filteredStockInfoList.observeAsState(emptyList())
    val listState = rememberLazyListState() // 用於控制滾動狀態

    // 首次載入或列表更新時滾動到頂部
    LaunchedEffect(stockInfoList) {
        if (stockInfoList.isNotEmpty()) {
            listState.scrollToItem(0)// 滾動到頂部
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 列表内容
        if (stockInfoList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize() // 滿版
                , contentAlignment = Alignment.Center // 子組件居中對齊
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "無法獲取有效數據")
                    Button(onClick = { viewModel.fetchStockInfo() }) {
                        Text(text = "重試", style = Typography.displayMedium)
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                // 列表區域
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    state = listState, // 管理滾動狀態
                ) {
                    items(stockInfoList) { stock ->
                        StockCard(stockInfo = stock)
                    }
                }
            }
        }
    }
}