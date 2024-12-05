package com.juacie.cathay.stock.app.main.view

import androidx.compose.runtime.Composable
import com.juacie.cathay.stock.app.base.BaseActivity
import com.juacie.cathay.stock.app.main.viewModel.StockViewModel

class StockActivity : BaseActivity<StockViewModel>() {
    @Composable
    override fun ScreenContent() {
        StockListScreen(viewModel = viewModel)
    }
}
