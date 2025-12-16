package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.finance.response.RentHouseLoanProductItemDto
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun RentHouseLoanResultScreen(
    modifier: Modifier,
    onNavigateBack: () -> Unit,
    item: RentHouseLoanProductItemDto?
) {
    val ProductTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 20.sp
    )
    val scrollState = rememberScrollState()

    fun String.formatYmd(): String =
        if (length == 8) "${substring(0, 4)}-${substring(4, 6)}-${substring(6, 8)}" else this

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_mypage_arrow),
                    contentDescription = "뒤로가기",
                    modifier = Modifier
                        .rotate(180f)
                        .clickable { onNavigateBack() }
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "은행 상품 정보",
                    color = MoneyMateTheme.colors.darkGray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        fontSize = 20.sp
                    )
                )
            }
            Box(modifier = Modifier.weight(1f))
        }

        Text(
            text = item?.productName ?: "-",
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 24.sp
            ),
            modifier = Modifier
                .padding(top = 36.dp, bottom = 50.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            InfoRow(label = "은행명", value = item?.bankName, isPhoneNum = false)
            InfoRow(label = "대출금리 유형", value = item?.lendRateType, isPhoneNum = false)
            InfoRow(label = "최저금리", value = item?.lendRateMin?.let { "$it%" }, isPhoneNum = false)
            InfoRow(label = "최고금리", value = item?.lendRateMax?.let { "$it%" }, isPhoneNum = false)
            InfoRow(
                label = "전월취급평균금리",
                value = item?.lendRateAvg?.let { "$it%" },
                isPhoneNum = false
            )
            InfoRow(
                label = "대출 부대비용",
                value = item?.loanInciExpn,
                isMultiLine = true,
                isPhoneNum = false
            )
            InfoRow(
                label = "중도상환 수수료",
                value = item?.erlyRpayFee,
                isMultiLine = true,
                isPhoneNum = false
            )
            InfoRow(label = "연체이자율", value = item?.dlyRate, isMultiLine = true, isPhoneNum = false)
            InfoRow(label = "대출한도", value = item?.loanLmt, isMultiLine = true, isPhoneNum = false)
            InfoRow(label = "대출상환유형", value = item?.rpayType, isPhoneNum = false)
            InfoRow(label = "가입 방법", value = item?.joinWay, isPhoneNum = false)
            InfoRow(label = "공시 시작일", value = item?.dclsStrtDay?.formatYmd(), isPhoneNum = false)
            InfoRow(
                label = "공시 종료일",
                value = item?.dclsEndDay?.takeUnless { it.isBlank() }?.formatYmd(),
                isPhoneNum = false
            )
            InfoRow(label = "상담 전화번호", value = item?.callNum, isPhoneNum = true)
        }
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String?,
    isMultiLine: Boolean = false,
    isPhoneNum: Boolean
) {
    val finalValue = if (value.isNullOrBlank()) "-" else value
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = if (isMultiLine) Alignment.Top else Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(end = 16.dp)
        )
        if (isPhoneNum) {
            Text(
                text = finalValue,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.Underline
                ),
                color = MoneyMateTheme.colors.darkGray,
                modifier = Modifier.clickable {
                    val cleanNum = finalValue.replace(Regex("[^0-9]"), "")
                    if (cleanNum.isNotEmpty()) {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = "tel:$cleanNum".toUri()
                        }
                        context.startActivity(intent)
                    }
                }
            )
        } else {
            Text(
                text = finalValue,
                color = MoneyMateTheme.colors.darkGray,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.End
            )
        }

    }
}