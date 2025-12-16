package com.moneymate.moneymate.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun BottomFullWidthButton(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MoneyMateTheme.typography.head_03_B_16,
            color = contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomFullWidthButtonPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        BottomFullWidthButton(
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "버튼"
        ) {
            //onClick 작성
        }
        BottomFullWidthButton(
            containerColor = MoneyMateTheme.colors.neutral300,
            contentColor = MoneyMateTheme.colors.neutral500,
            text = "버튼"
        ) {
            //onClick 작성
        }
    }
}
