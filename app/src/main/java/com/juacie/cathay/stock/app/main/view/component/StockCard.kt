package com.juacie.cathay.stock.app.main.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juacie.cathay.stock.R
import com.juacie.cathay.stock.app.kit.function.FuncObj
import com.juacie.cathay.stock.app.main.model.StockInfo
import com.juacie.cathay.stock.ui.theme.CathayStockTheme
import com.juacie.cathay.stock.ui.theme.Typography

@Composable
fun StockCard(stockInfo: StockInfo) {

    val displayColor: Color = MaterialTheme.colorScheme.onSurface
    val redColor: Color = colorResource(id = R.color.color_e00000)
    val greenColor: Color = colorResource(id = R.color.color_037351)

    // 用來控制 AlertDialog 顯示或隱藏的狀態
    val openDialog = remember { mutableStateOf(false) }
    // 當 openDialog 為 true 時，顯示 AlertDialog
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false // 當點擊外部或按下返回時隱藏對話框
            },
            title = {
                Column(modifier = Modifier.padding(16.dp)) {
                    //股票基本資訊
                    Text(text = stockInfo.code, style = Typography.labelSmall)
                    Text(text = stockInfo.name, style = Typography.titleLarge)
                }
            },
            text = {
                StockRow(
                    label1 = "本益比",
                    value1 = stockInfo.peRatio,
                    label2 = "殖利率",
                    value2 = stockInfo.dividendYield,
                    label3 = "股票淨值比",
                    value3 = stockInfo.pbRatio
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false // 按下確認時隱藏對話框
                    }
                ) {
                    Text("了解")
                }
            },
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                openDialog.value = true // 點擊 Card 顯示對話框
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //股票基本資訊
            Text(text = stockInfo.code, style = Typography.labelSmall)
            Text(text = stockInfo.name, style = Typography.titleLarge)

            //行情資訊
            StockRow(
                label1 = "開盤價",
                value1 = stockInfo.openingPrice,
                label2 = "收盤價",
                value2 = stockInfo.closingPrice,
                color2 = FuncObj.checkColor(
                    stockInfo.closingPrice,
                    stockInfo.monthlyAveragePrice,
                    redColor,
                    greenColor,
                    displayColor
                ),
                modifier = Modifier.padding(start = 24.dp)
            )
            StockRow(
                label1 = "最高價",
                value1 = stockInfo.highestPrice,
                label2 = "最低價",
                value2 = stockInfo.lowestPrice,
                modifier = Modifier.padding(start = 24.dp)
            )
            StockRow(
                label1 = "漲跌價差",
                value1 = stockInfo.change,
                color1 = FuncObj.checkColor(
                    stockInfo.change, "0", redColor, greenColor, displayColor
                ),
                label2 = "月平均價",
                value2 = stockInfo.monthlyAveragePrice,
                modifier = Modifier.padding(start = 24.dp)
            )
            //成交資訊
            StockRow(
                label1 = "成交筆數",
                value1 = stockInfo.transaction,
                label2 = "成交股數",
                value2 = if (stockInfo.tradeVolume.isNullOrEmpty()) "N/A"
                else FuncObj.formatDouble(stockInfo.tradeVolume.toDouble()),
                label3 = "成交金額",
                value3 = if (stockInfo.tradeValue.isNullOrEmpty()) "N/A"
                else "$ ${FuncObj.formatDouble(stockInfo.tradeValue.toDouble())}",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun StockRow(
    label1: String,
    value1: String?,
    color1: Color = MaterialTheme.colorScheme.onSurface,
    label2: String? = null,
    value2: String? = null,
    color2: Color = MaterialTheme.colorScheme.onSurface,
    label3: String? = null,
    value3: String? = null,
    color3: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // 均勻分布空間
    ) {
        StockItem(label1, value1, color1)
        if (label2 != null) {
            StockItem(label2, value2, color2)
        }
        if (label3 != null) {
            StockItem(label3, value3, color3)
        }
    }
}

@Composable
fun StockItem(
    label: String,
    value: String?,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Text(
        text = label,
        textAlign = TextAlign.End,
        style = Typography.displayMedium
    )
    Text(
        text = value ?: "N/A",
        textAlign = TextAlign.Center,
        color = valueColor,
        style = Typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
fun StockCardPreview() {
    CathayStockTheme {
        StockCard(StockInfo(name = "123", code = "0050", peRatio = "123", dividendYield = "123"))
    }
}
