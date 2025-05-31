package com.moneymate.moneymate.ui.asset.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun AccountItem(
    modifier: Modifier = Modifier,
    name: String,
    value: Int,
    onClick: () -> Unit
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
                Text(text = value.toDecimalFormat()+"원", style = MoneyMateTheme.typography.head_02_B_20)
            }
        }
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(70.dp, 35.dp)
                .border(
                    width = 1.dp,
                    color = MoneyMateTheme.colors.deepBlue,
                    shape = RoundedCornerShape(10.dp)
                )
                .align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MoneyMateTheme.colors.white,
                contentColor = MoneyMateTheme.colors.deepBlue
            )
        ) {
            Text(
                text =  "거래내역",
                style = MoneyMateTheme.typography.body_02_R_12
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountItemPreview() {
    Column {
        AccountItem(
            name = "KB 국민은행",
            value = 888888,
        ) { }
        AccountItem(
            name = "KB청년도약계좌",
            value = 888888,
        ) { }
        AccountItem(
            name = "토스증권",
            value = 888888,
        ) { }
    }
}