package nextstep.signup.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.validator.UserName
import nextstep.signup.validator.getErrorString

@Composable
internal fun UserNameTextFiled(
    userName: UserName,
    onValueChange: (UserName) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_user_name),
        text = userName.textValue,
        onValueChange = { onValueChange(UserName.of(it)) },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        isError = userName.isInvalid(),
        errorMessage = userName.getErrorString()
    )
}


@Preview(showBackground = true)
@Composable
private fun UserNameTextFieldPreview(
    @PreviewParameter(UsernamePreviewParameterProvider::class) str: String,
) {
    var userName by remember { mutableStateOf(UserName.of(str)) }
    UserNameTextFiled(
        userName = userName,
        onValueChange = { userName = it }
    )
}

private class UsernamePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "김수한무거북이",
            "이",
            "2용우!",
            "컴포즈",
            "",
        )
}
