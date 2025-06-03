package com.moneymate.moneymate.ui.asset.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun AccountContainer(
    modifier: Modifier = Modifier,
    name: String,
    accountList: List<AccountInfo>,
    onAddClick: () -> Unit,
    onItemClick: (AccountInfo) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MoneyMateTheme.colors.white)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.size(22.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MoneyMateTheme.typography.head_02_B_20
                )
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .border(
                            width = 1.dp,
                            color = MoneyMateTheme.colors.deepBlue,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            onAddClick()
                        }
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                        tint = MoneyMateTheme.colors.deepBlue
                    )
                }
            }
            Spacer(modifier = Modifier.size(22.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MoneyMateTheme.colors.deepBlue
            )
        }
        if (accountList.isNotEmpty()) {
            for (asset in accountList) {
                AccountItem(
                    name = asset.name,
                    value = asset.balance,
                    onClick = {
                        onItemClick(asset)
                    }
                )
            }
        } else {
            Spacer(modifier = Modifier.size(28.dp))
            Text(
                text = "등록된 계좌가 없습니다.",
                style = MoneyMateTheme.typography.head_03_SB_16
            )
            Spacer(modifier = Modifier.size(28.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AccountContainerPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.backgroundWhite)
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AccountContainer(
            name = "입출금 계좌",
            accountList = listOf(
                AccountInfo(
                    uid = "2",
                    bankCode = "004",
                    name = "청년도약계좌",
                    type = "예적금",
                    number = "02004200000000",
                    balance = 1000000
                )
            ),
            onAddClick = {},
            onItemClick = {}
        )
    }
}