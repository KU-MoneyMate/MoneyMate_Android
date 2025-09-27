package com.moneymate.moneymate.ui.finance.component.FinancialProduct

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun FinancialProductCheckbox(
    label: String,
    isChecked: Boolean,
    onToggle: () -> Unit
){
    Row (
        modifier = Modifier
            .clickable{ onToggle() },
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            painter = painterResource(
                if(isChecked) R.drawable.ic_financial_checkbox_checked
                else R.drawable.ic_financial_checkbox_unchecked
            ),
            contentDescription = "checkbox",
            modifier = Modifier,
                //.background(MoneyMateTheme.colors.backgroundWhite),
            tint = androidx.compose.ui.graphics.Color.Unspecified
        )
        Spacer(modifier=Modifier.width(4.dp))
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 18.sp,
                color = MoneyMateTheme.colors.darkGray
            )
        )
    }
}