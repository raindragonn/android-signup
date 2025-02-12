package nextstep.signup.validator

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nextstep.signup.R

sealed interface UserName {
    val textValue: String

    fun isValid() = this is Valid
    fun isInvalid() = this is InValid

    fun getInvalidType() = (this as? InValid)?.type

    data class Valid(override val textValue: String) : UserName
    data class Empty(override val textValue: String) : UserName

    data class InValid(
        override val textValue: String,
        val type: Type
    ) : UserName {

        enum class Type {
            LENGTH,
            FORMAT,
        }
    }

    companion object {
        private const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"
        private val regex by lazy { USERNAME_REGEX.toRegex() }

        fun of(userName: String): UserName {
            if (userName.isEmpty()) return Empty(userName)
            if (userName.length !in 2..5) return InValid(userName, InValid.Type.LENGTH)

            return when (userName.matches(regex)) {
                true -> Valid(userName)
                false -> InValid(userName, InValid.Type.FORMAT)
            }
        }
    }
}

@Composable
fun UserName.getErrorString(): String? {
    return when (this) {
        is UserName.Valid, is UserName.Empty -> null
        is UserName.InValid -> {
            when (this.type) {
                UserName.InValid.Type.LENGTH -> stringResource(R.string.invalid_length_username)
                UserName.InValid.Type.FORMAT -> stringResource(R.string.invalid_format_username)
            }
        }
    }
}
