package com.moneymate.moneymate.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun MoneyMateTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.None),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp)),
        value = text,
        onValueChange = onValueChange,
        placeholder = {
            placeholder?.invoke()
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MoneyMateTheme.colors.backgroundWhite,
            focusedTextColor = MoneyMateTheme.colors.darkGray,
            unfocusedContainerColor = MoneyMateTheme.colors.backgroundWhite,
            unfocusedTextColor = MoneyMateTheme.colors.darkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = MoneyMateTheme.colors.lightGray,
            unfocusedPlaceholderColor = MoneyMateTheme.colors.lightGray,
        ),
    )
}

@Preview
@Composable
private fun MoneyMateTextFieldPreview(){
    var text by rememberSaveable { mutableStateOf("") }

    MoneyMateTextField(
        modifier = Modifier.fillMaxWidth(), // 크기 지정 필요한 경우 Modifier를 통해 지정(예: fillMaxWidth(), size() 등)
        text = text,
        onValueChange = {
            text = it
        },
        placeholder = {
            Text(
                text = "placeholder",
                style = MoneyMateTheme.typography.body_01_M_14
            )
        }
    )
}