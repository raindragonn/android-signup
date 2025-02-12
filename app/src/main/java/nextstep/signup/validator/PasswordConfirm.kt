package nextstep.signup.validator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

sealed interface PasswordConfirm {
    val textValue: String

    fun isValid() = this is Valid
    fun isInvalid() = this is InValid

    fun getInvalidType() = (this as? InValid)?.type

    data class Valid(override val textValue: String) : PasswordConfirm
    data class Empty(override val textValue: String) : PasswordConfirm

    data class InValid(
        override val textValue: String,
        val type: Type
    ) : PasswordConfirm {

        enum class Type {
            NOT_MATCH
        }
    }

    companion object {
        fun of(passwordConfirm: String, password: String = ""): PasswordConfirm {
            if (passwordConfirm.isEmpty()) return Empty(passwordConfirm)

            return when (password == passwordConfirm) {
                true -> Valid(passwordConfirm)
                false -> InValid(passwordConfirm, InValid.Type.NOT_MATCH)
            }
        }
    }
}

@Composable
fun PasswordConfirm.getErrorString(): String? {
    return when (this) {
        is PasswordConfirm.Valid, is PasswordConfirm.Empty -> null
        is PasswordConfirm.InValid -> {
            when (this.type) {
                PasswordConfirm.InValid.Type.NOT_MATCH -> stringResource(R.string.not_match_password)
            }
        }
    }
}
