package com.moneymate.moneymate.ui.finance.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockMarketScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dropdownList = listOf("경제 지표", "국내 주식", "해외 주식")
    var selectedDropdownMenu by rememberSaveable { mutableStateOf(dropdownList[0]) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
    ) {
        TopAppBar(
            modifier = Modifier,
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onNavigateBack()
                            },
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back icon"
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "증시 정보",
                        style = MoneyMateTheme.typography.head_02_B_20
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MoneyMateTheme.colors.white
            )
        )
        Spacer(modifier = Modifier.size(20.dp))
        ExposedDropdownMenuBox(
            modifier = Modifier
                .width(170.dp)
                .padding(horizontal = 20.dp),
            expanded = dropdownExpanded,
            onExpandedChange = { dropdownExpanded = !dropdownExpanded },
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    ),
                value = selectedDropdownMenu,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded) },
                // 스타일 제거를 위해 colors를 투명하게 설정
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent
                ),
            )
            ExposedDropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier
                    .wrapContentWidth()
                    .background(color = MoneyMateTheme.colors.backgroundWhite, shape = RoundedCornerShape(8.dp))
            ) {
                dropdownList.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedDropdownMenu = dropdownList[index]
                            dropdownExpanded = false
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StockMarketScreenPreview() {
    StockMarketScreen(
        modifier = Modifier.fillMaxSize(),
        onNavigateBack = {}
    )
}