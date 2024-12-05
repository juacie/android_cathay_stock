package com.juacie.cathay.stock.app.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juacie.cathay.stock.ui.theme.CathayStockTheme
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<out ViewModel : BaseViewModel> : ComponentActivity(),
    IViewModel<ViewModel> {
    //==============================================================================================
    //region declare

    final override val viewModel: ViewModel

    //endregion declare
    //==============================================================================================
    //region View Implementation

    init {
        viewModel = findViewModelClass().newInstance()
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let { base ->
            val newConfig = Configuration(base.resources.configuration)
            newConfig.fontScale = 1.0f // 禁用字體缩放
            applyOverrideConfiguration(newConfig)
        }
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CathayStockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenContent()
                }
            }
        }
    }
    //endregion View Implementation
    //==============================================================================================
    //region function

    private fun findViewModelClass(): Class<ViewModel> {
        var thisClass: Class<*> = this.javaClass
        while (true) {
            (thisClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.firstOrNull()
                ?.let {
                    return it as Class<ViewModel>
                } ?: run {
                thisClass = thisClass.superclass ?: throw IllegalArgumentException()
            }
        }
    }

    //endregion function
    //==============================================================================================
    @Composable
    abstract fun ScreenContent()
}