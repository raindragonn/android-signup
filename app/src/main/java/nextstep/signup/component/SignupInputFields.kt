package nextstep.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.model.SignupValidations
import nextstep.signup.validator.Email
import nextstep.signup.validator.Password
import nextstep.signup.validator.PasswordConfirm
import nextstep.signup.validator.UserName

@Composable
internal fun SignupInputFields(
    onValidation: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var userName by remember { mutableStateOf(UserName.of("")) }
    var email by remember { mutableStateOf(Email.of("")) }
    var password by remember { mutableStateOf(Password.of("")) }
    var passwordConfirm by remember { mutableStateOf(PasswordConfirm.of("")) }

    val isValidations by remember {
        derivedStateOf {
            SignupValidations(
                userName.isValid(),
                email.isValid(),
                password.isValid(),
                passwordConfirm.isValid()
            ).isAllValidation()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UserNameTextFiled(
            userName = userName,
            onValueChange = {
                userName = it
                onValidation(isValidations)
            },
        )
        EmailTextFiled(
            email = email,
            onValueChange = {
                email = it
                onValidation(isValidations)
            },
        )
        PasswordTextFiled(
            password = password,
            onValueChange = {
                password = it
                passwordConfirm = PasswordConfirm.of(passwordConfirm.textValue, it.textValue)
                onValidation(isValidations)
            },
        )
        PasswordConfirmTextFiled(
            passwordText = password.textValue,
            passwordConfirm = passwordConfirm,
            onValueChange = {
                passwordConfirm = it
                onValidation(isValidations)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupFieldsPreview() {
    SignupInputFields(onValidation = {})
}
