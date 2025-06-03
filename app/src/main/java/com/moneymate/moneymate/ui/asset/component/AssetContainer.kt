package com.moneymate.moneymate.ui.asset.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.moneymate.moneymate.data.dto.account.response.AssetInfo
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun AssetContainer(
    modifier: Modifier = Modifier,
    name: String,
    assetList: List<AssetInfo>,
    onAddClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
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
        if (assetList.isNotEmpty()){
            for(asset in assetList) {
                AssetItem(
                    uId = 1,
                    type = asset.type,
                    name = asset.name,
                    value = asset.price
                )
            }
        } else {
            Spacer(modifier = Modifier.size(28.dp))
            Text(
                text = "등록된 자산이 없습니다.",
                style = MoneyMateTheme.typography.head_03_SB_16
            )
            Spacer(modifier = Modifier.size(28.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AssetContainerPreview() {
    Column {
        AssetContainer(
            name = "부동산",
            assetList = listOf(
                AssetInfo(
                    uid = 1,
                    name = "반포자이",
                    type = "부동산",
                    price = 40000000
                )
            ),
            onAddClick = {}
        )
    }
}