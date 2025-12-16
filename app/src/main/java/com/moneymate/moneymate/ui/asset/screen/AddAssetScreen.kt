package com.moneymate.moneymate.ui.asset.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
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
    onNavigateBack: () -> Unit,
    viewModel: AssetViewModel = hiltViewModel()
) {
    var assetName by rememberSaveable { mutableStateOf("") }
    var assetValue by rememberSaveable { mutableStateOf("") }

    val keyboardFocusManager = LocalFocusManager.current

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MoneyMateTheme.colors.white)
        ) {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            modifier = Modifier
                                .clickable { onNavigateBack() },
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "back icon"
                        )
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "투자 자산 정보 등록",
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
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .background(MoneyMateTheme.colors.white)
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "사용자가 보유한 투자 자산 정보를 등록합니다.\n투자 자산의 가치와 이름을 입력해주세요.",
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
                            text = "등록할 투자 자산의 이름을 입력해주세요.",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {keyboardFocusManager.moveFocus(FocusDirection.Down)})
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
                            text = "등록할 투자 자산의 가치를 입력해주세요.",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {keyboardFocusManager.clearFocus()})
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
            viewModel.registerAsset(
                name = assetName,
                price = assetValue.toLong()
            )
            onNavigateBack()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddAssetScreenPreview() {
    AddAssetScreen(
        modifier = Modifier,
        onNavigateBack = {},
    )
}