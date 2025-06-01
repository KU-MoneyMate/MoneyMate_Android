package com.moneymate.moneymate.ui.asset.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssetScreen(
    modifier: Modifier = Modifier,
//    viewModel: AssetViewModel = hiltViewModel()
) {
    var assetName by rememberSaveable { mutableStateOf("") }
    var assetValue by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            modifier = Modifier,
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "back icon"
                        )
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "자산 등록",
                            style = MoneyMateTheme.typography.head_02_B_20
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MoneyMateTheme.colors.white
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "사용자가 보유한 부동산 정보를 등록합니다.\n부동산의 가치와 이름을 입력해주세요.",
                    style = MoneyMateTheme.typography.head_03_R_16
                )
                // 은행명
                Spacer(modifier = Modifier.size(34.dp))
                Text(
                    text = "이름",
                    style = MoneyMateTheme.typography.head_03_SB_16
                )
                Spacer(modifier = Modifier.size(15.dp))
                MoneyMateTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = assetName,
                    onValueChange = {
                        assetName = it
                    },
                    placeholder = {
                        Text(
                            text = "등록할 계좌의 은행명을 입력해주세요.",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    }
                )
                // 계좌번호
                Spacer(modifier = Modifier.size(34.dp))
                Text(
                    text = "자산 가치(원)",
                    style = MoneyMateTheme.typography.head_03_SB_16
                )
                Spacer(modifier = Modifier.size(15.dp))
                MoneyMateTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = assetValue,
                    onValueChange = {
                        assetValue = it
                    },
                    placeholder = {
                        Text(
                            text = "등록할 부동산의 자산 가치를 입력해주세요.",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    }
                )
            }
        }
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "등록"
        ) {
            // TODO
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddAssetScreenPreview() {
    AddAssetScreen()
}