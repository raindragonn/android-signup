package nextstep.signup.validator

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class EmailTest(
    private val inputText: String,
    private val outputResult: Email.InValid.Type,
) {
    @Test
    fun `이메일은_형식이_틀리면_검증되지않음`() {
        // given
        val email = Email.of(inputText)

        //then
        assert(email is Email.InValid)
        assert(email.getInvalidType() == outputResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf("gmail.com", Email.InValid.Type.FORMAT),
                arrayOf("raindragonn", Email.InValid.Type.FORMAT),
                arrayOf("raindragonn@gmail", Email.InValid.Type.FORMAT),
            )
        }
    }
}
