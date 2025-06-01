package com.moneymate.moneymate.ui.asset.component

import android.widget.Space
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.data.dto.account.response.Transaction
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun TransactionHistoryItem(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = transaction.time.substring(0,5),
            style = MoneyMateTheme.typography.body_01_R_14,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = transaction.destination,
            style = MoneyMateTheme.typography.body_01_M_14,
            modifier = Modifier.weight(1f)
        )

        val amount = if (transaction.outAmount > 0) "- %,d원".format(transaction.outAmount) else "+ %,d원".format(transaction.inAmount)

        Text(
            text = amount,
            style = MoneyMateTheme.typography.head_03_B_16.copy(
                color = MoneyMateTheme.colors.darkGray
            )
        )
    }
}
