package nextstep.signup.validator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

sealed interface Email {
    val textValue: String

    fun isValid() = this is Valid
    fun isInvalid() = this is InValid

    fun getInvalidType() = (this as? InValid)?.type

    data class Valid(override val textValue: String) : Email
    data class Empty(override val textValue: String) : Email

    data class InValid(
        override val textValue: String,
        val type: Type
    ) : Email {

        enum class Type {
            FORMAT,
        }
    }

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private val regex by lazy { EMAIL_REGEX.toRegex() }

        fun of(email: String): Email {
            if (email.isEmpty()) return Empty(email)

            return when (email.matches(regex)) {
                true -> Valid(email)
                false -> InValid(email, InValid.Type.FORMAT)
            }
        }
    }
}

@Composable
fun Email.getErrorString(): String? {
    return when (this) {
        is Email.Valid, is Email.Empty -> null
        is Email.InValid -> {
            when (this.type) {
                Email.InValid.Type.FORMAT -> stringResource(R.string.invalid_format_email)
            }
        }
    }
}
