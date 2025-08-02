package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moneymate.moneymate.ui.theme.defaultMoneyMateTypography
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.util.auth.isValidId
import kotlinx.coroutines.launch

@Composable
fun SignUpIDScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    onNext: () -> Unit
) {
    var id by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val idCheckStatus by viewModel.idCheckStatus.collectAsStateWithLifecycle()
    var buttonEnabled by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(idCheckStatus) {
        when (idCheckStatus) {
            "Conflict" -> {
                buttonEnabled = false
                snackbarHostState.showSnackbar("이미 존재하는 id입니다.")
                viewModel.clearIdCheckStatus()
            }
            "OK" -> {
                buttonEnabled = true
                snackbarHostState.showSnackbar("사용 가능한 id입니다.")
                viewModel.clearIdCheckStatus()
            }
            null -> { /* 초기 상태 - 아무 동작 하지 않음 */ }
            else -> {
                buttonEnabled = false
                snackbarHostState.showSnackbar("다시 시도해주세요")
                viewModel.clearIdCheckStatus()
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MoneyMateTheme.colors.white),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(75.dp))

                Text(
                    text = "아이디를 입력해주세요",
                    color = MoneyMateTheme.colors.black,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold))
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "영문 8자 이상",
                    color = MoneyMateTheme.colors.neutral500,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular))
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MoneyMateTextField(
                        modifier = Modifier.size(250.dp, 50.dp),
                        text = id,
                        onValueChange = { id = it },
                        placeholder = {
                            Text(
                                text = "아이디를 입력해주세요.",
                                style = MoneyMateTheme.typography.body_01_M_14
                            )
                        }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(
                        onClick = {
                            if (id.isValidId()) {
                                viewModel.checkUserId(userId = id)
                            } else {
                                buttonEnabled = false
                                scope.launch {
                                    snackbarHostState.showSnackbar("유효한 id를 입력해주세요")
                                }
                            }
                        },
                        contentPadding = PaddingValues( 10.dp, 0.dp),
                        modifier = Modifier
                            .height(50.dp)
                            .width(200.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MoneyMateTheme.colors.deepBlue,
                            contentColor = MoneyMateTheme.colors.white
                        )
                    ) {
                        Text(
                            text = "중복확인",
                            style = MoneyMateTheme.typography.body_02_SB_12,
                            color = MoneyMateTheme.colors.white
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomFullWidthButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 30.dp),
                    text = "다음",
                    enabled =  buttonEnabled,
                    containerColor = if(buttonEnabled) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.neutral300,
                    contentColor = if(buttonEnabled) Color.White else MoneyMateTheme.colors.neutral500,
                ) {
                    viewModel.saveSignupId(
                        userId = id
                    ){
                        onNext()
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 100.dp)
        )
    }
}

@Preview
@Composable
private fun SignupIdScreenPreview() {
    val viewModel: AuthViewModel = viewModel()
    SignUpIDScreen(
        modifier = Modifier,
        viewModel = viewModel,
    ) {

    }
}
