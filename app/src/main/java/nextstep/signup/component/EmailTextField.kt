package nextstep.signup.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.validator.Email
import nextstep.signup.validator.getErrorString

@Composable
internal fun EmailTextFiled(
    email: Email,
    onValueChange: (Email) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignupTextField(
        modifier = modifier,
        label = stringResource(R.string.signup_label_email),
        text = email.textValue,
        onValueChange = { onValueChange(Email.of(it)) },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        isError = email.isInvalid(),
        errorMessage = email.getErrorString()
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFiledPreview(
    @PreviewParameter(EmailTextFieldPreviewParameterProvider::class) text: String,
) {
    EmailTextFiled(
        email = Email.of(text),
        onValueChange = { },
    )
}

private class EmailTextFieldPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "raindragonn!gmail.com",
            "raindragonn@gmail.com",
        )
}
