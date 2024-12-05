package com.juacie.cathay.stock.app.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.juacie.cathay.stock.app.main.model.StockInfo
import com.juacie.cathay.stock.app.main.view.component.ScrollableStockList
import com.juacie.cathay.stock.app.main.view.component.SearchAndMenuBar
import com.juacie.cathay.stock.app.main.viewModel.StockViewModel
import com.juacie.cathay.stock.ui.theme.CathayStockTheme
import com.juacie.cathay.stock.ui.theme.Typography


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(viewModel: StockViewModel) {
    // 用來控制焦點的 FocusManager
    val focusManager = LocalFocusManager.current

    // 下拉刷新狀態
    val isRefreshing by viewModel.isLoading.collectAsState()

    // PullRefresh 狀態管理
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        viewModel.fetchStockInfo() // 調用刷新邏輯
    })

    // BottomSheet 狀態管理
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    // 監聽搜尋文字輸入
    val searchQuery by viewModel.searchQuery.collectAsState()

    //載入股票資訊
    LaunchedEffect(Unit) {
        viewModel.fetchStockInfo()
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), // 設定圓角
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            BottomSheetContent(viewModel) { showBottomSheet = false }
        }
    }
    // 最外層的 Box，可以攔截點擊事件
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                // 避免影響內部可點擊元件
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus() // 清除 TextField 焦點
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchAndMenuBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onMenuClick = { showBottomSheet = true }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState) // 綁定刷新狀態
            ) {
                ScrollableStockList(viewModel)

                // 下拉刷新指示器
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(viewModel: StockViewModel, onHidden: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "依股票代號降序",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    viewModel.sortStockInfoList(false)
                    // 隱藏 BottomSheet
                    onHidden.invoke()
                }
        )
        Text(
            text = "依股票代號升序",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    viewModel.sortStockInfoList(true)  // 隱藏 BottomSheet
                    onHidden.invoke()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StockListScreenPreview() {
    val fakeViewModel = object : StockViewModel() {
        override val stockInfoList = MutableLiveData(
            listOf(
                StockInfo(name = "Stock A", code = "001", peRatio = "10", dividendYield = "5%"),
                StockInfo(name = "Stock B", code = "002", peRatio = "15", dividendYield = "4%")
            )
        )
    }
    CathayStockTheme {
        StockListScreen(viewModel = fakeViewModel)
    }
}