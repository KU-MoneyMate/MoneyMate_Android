package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.manage.screen.AssetStatisticsScreen
import com.moneymate.moneymate.ui.manage.screen.OutlinedInputField
import com.moneymate.moneymate.ui.manage.screen.RowWithTwoInputs
import com.moneymate.moneymate.ui.manage.screen.SectionTitle
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.moneymate.moneymate.ui.mypage.MyPageViewModel

@Composable
fun UserInfoScreen(
    modifier : Modifier = Modifier,
    onNavigateBack : () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val userInfo by viewModel.userInfo.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
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
                modifier = Modifier.weight(3f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "계정 정보 조회 및 수정",
                    color = MoneyMateTheme.colors.darkGray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        fontSize = 20.sp
                    )
                )
            }
            Box(modifier = Modifier.weight(1f))
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "아이디",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = userInfo.userId,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                ),
                modifier = Modifier.width(141.dp)
            )
        }
        OutlinedInputField(
            "이름",
            userInfo.name,
            onValueChange = viewModel::updateName,
            "",
            2
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "생년월일",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MoneyMateTextField(
                text = userInfo.year,
                onValueChange = viewModel::updateYear,
                modifier = Modifier.width(90.dp),
                placeholder = {
                    Text(
                        text = "YYYY",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = "년",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 16.sp
                )
            )
            MoneyMateTextField(
                text = userInfo.month,
                onValueChange = viewModel::updateMonth,
                modifier = Modifier.width(90.dp),
                placeholder = {
                    Text(
                        text = "MM",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = "월",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 16.sp
                )
            )
            MoneyMateTextField(
                text = userInfo.day,
                onValueChange = viewModel::updateDay,
                modifier = Modifier.width(90.dp),
                placeholder = {
                    Text(
                        text = "DD",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = "일",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 16.sp
                )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "전화번호",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MoneyMateTextField(
                text = userInfo.phone1,
                onValueChange = viewModel::updatePhone1,
                modifier = Modifier.width(100.dp),
                placeholder = {
                    Text(
                        text = "010",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )

            Image(
                painter = painterResource(id = R.drawable.img_signin_dash),
                contentDescription = "phone num dash",
                modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
            )

            MoneyMateTextField(
                text = userInfo.phone2,
                onValueChange = viewModel::updatePhone2,
                modifier = Modifier.width(100.dp),
                placeholder = {
                    Text(
                        text = "1234",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )

            Image(
                painter = painterResource(id = R.drawable.img_signin_dash),
                contentDescription = "phone num dash",
                modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
            )

            MoneyMateTextField(
                text = userInfo.phone3,
                onValueChange = viewModel::updatePhone3,
                modifier = Modifier.width(100.dp),
                placeholder = {
                    Text(
                        text = "1234",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        // 조회하기 버튼
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "수정하기"
        ) {
            viewModel.updateUserInfo()
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun UserInfoScreenPreview() {
    UserInfoScreen(
        modifier = Modifier,
        onNavigateBack = {},
        viewModel = hiltViewModel()
    )
}