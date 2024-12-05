package com.juacie.cathay.stock.app.main.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.juacie.cathay.stock.R
import com.juacie.cathay.stock.ui.theme.Typography

@Composable
internal fun SearchAndMenuBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 搜尋欄位
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
            label = { Text("股票代號", style = Typography.labelSmall, color = Color.DarkGray) },
            textStyle = Typography.titleLarge,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedContainerColor = colorResource(id = R.color.color_f2f2f2),
                unfocusedContainerColor = Color.LightGray,
            )
        )
        // Menu按鈕
        Box(
            modifier = Modifier
                .size(48.dp)
                .padding(start = 4.dp)
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.color_676767),
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = colorResource(id = R.color.color_f2f2f2),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(onClick = onMenuClick),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
                contentDescription = "Menu"
            )
        }
    }
}