package com.juacie.cathay.stock.app.kit.function

import androidx.compose.ui.graphics.Color
import java.text.NumberFormat
import java.util.Locale

object FuncObj {
    /**
     * 針對金錢的顯示，進行額外判斷。
     *
     * 1. 如果值是整數的話，輸出為整數。舉例： input(50.0)->output(50)
     * 2. 如果值是小數的話，輸出至小數點以下第二位。舉例：input(50.067)->output(50.07) / input(50.6)->output(50.60)
     */
    fun formatDouble(value: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US) // 指定美式格式（可根據需求改變 Locale）
        return if (value % 1.0 == 0.0) {
            formatter.format(value.toLong()) // 整數部分，轉換為 Long 格式化
        } else {
            "%.2f".format(value)
        }
    }

    /**
     * 針對顯示的顏色，依照比對結果後調色
     */
    fun checkColor(
        target: String?, aim: String?, redColor: Color, greenColor: Color, defaultColor: Color
    ): Color {
        return when {
            target.isNullOrEmpty() || aim.isNullOrEmpty() -> defaultColor
            target.toDouble() > aim.toDouble() -> redColor
            target.toDouble() < aim.toDouble() -> greenColor
            else -> defaultColor
        }
    }

}