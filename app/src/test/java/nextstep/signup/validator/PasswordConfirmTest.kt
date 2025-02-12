package nextstep.signup.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PasswordConfirmTest(
    private val passwordStr: String,
    private val passwordConfirmStr: String
) {

    @Test
    fun `비밀번호가_일치하지않으면_검증되지않음`() {
        val passwordConfirm = PasswordConfirm.of(passwordStr, passwordConfirmStr)

        //then
        assert(passwordConfirm is PasswordConfirm.InValid)
        assert(passwordConfirm.getInvalidType() == PasswordConfirm.InValid.Type.NOT_MATCH)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    "1q2w3e4rt",
                    "1q2w3e4r",
                ),
                arrayOf(
                    "1231231q",
                    "1231231w"
                ),
            )
        }
    }
}
