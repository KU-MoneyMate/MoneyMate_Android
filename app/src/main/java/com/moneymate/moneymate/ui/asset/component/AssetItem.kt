package com.moneymate.moneymate.ui.asset.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.moneymate.moneymate.util.toDecimalFormat

@Composable
fun AssetItem(
    modifier: Modifier = Modifier,
    uId: Int,
    name: String,
    value: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp, horizontal = 35.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(45.dp),
                painter = painterResource(R.drawable.img_dummy_asset),
                contentDescription = "asset icon",
                tint = MoneyMateTheme.colors.lightGray
            )
            Spacer(modifier = Modifier.size(17.dp))
            Column(
                modifier = Modifier,
            ) {
                Text(text = name, style = MoneyMateTheme.typography.body_02_R_12)
                Text(
                    text = value.toDecimalFormat() + if (uId == 1) "만원" else "원",
                    style = MoneyMateTheme.typography.head_02_B_20
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AssetItemPreview() {
    Column {
        AssetItem(
            uId = 1,
            name = "반포 자이",
            value = 425000
        )
        AssetItem(
            uId = 2,
            name = "엔비디아",
            value = 100000
        )
    }
}