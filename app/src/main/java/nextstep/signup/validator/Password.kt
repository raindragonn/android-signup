package nextstep.signup.validator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

sealed interface Password {
    val textValue: String

    fun isValid() = this is Valid
    fun isInvalid() = this is InValid

    fun getInvalidType() = (this as? InValid)?.type

    data class Valid(override val textValue: String) : Password
    data class Empty(override val textValue: String) : Password

    data class InValid(
        override val textValue: String,
        val type: Type
    ) : Password {

        enum class Type {
            LENGTH,
            FORMAT,
        }
    }

    companion object {
        private const val PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"
        private val regex by lazy { PASSWORD_REGEX.toRegex() }

        fun of(password: String): Password {
            if (password.isEmpty()) return Empty(password)
            if (password.length !in 8..16) return InValid(password, InValid.Type.LENGTH)

            return when (password.matches(regex)) {
                true -> Valid(password)
                false -> InValid(password, InValid.Type.FORMAT)
            }
        }
    }
}

@Composable
fun Password.getErrorString(): String? {
    return when (this) {
        is Password.Valid, is Password.Empty -> null
        is Password.InValid -> {
            when (this.type) {
                Password.InValid.Type.LENGTH -> stringResource(R.string.invalid_length_password)
                Password.InValid.Type.FORMAT -> stringResource(R.string.invalid_format_password)
            }
        }
    }
}
