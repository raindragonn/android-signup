package nextstep.signup.validator

import org.junit.Test

class PasswordTest {

    @Test
    fun `비밀번호는_8자_미만은_검증되지않음`() {
        val password = Password.of("1q2w3e4")

        //then
        assert(password is Password.InValid)
        assert(password.getInvalidType() == Password.InValid.Type.LENGTH)
    }

    @Test
    fun `비밀번호는_16자_초과는_검증되지않음`() {
        val password = Password.of("1q2w3e4r5t6y7u8i9")

        //then
        assert(password is Password.InValid)
        assert(password.getInvalidType() == Password.InValid.Type.LENGTH)
    }

    @Test
    fun `비밀번호는_영문을_포함하지_않으면_검증되지않음`() {
        val password = Password.of("12345678")

        //then
        assert(password is Password.InValid)
        assert(password.getInvalidType() == Password.InValid.Type.FORMAT)
    }

    @Test
    fun `비밀번호는_숫자를_포함하지_않으면_검증되지않음`() {
        val password = Password.of("abcdefgh")

        //then
        assert(password is Password.InValid)
        assert(password.getInvalidType() == Password.InValid.Type.FORMAT)
    }
}
