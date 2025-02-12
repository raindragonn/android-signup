package nextstep.signup.validator

import org.junit.Test

class UserNameTest {

    @Test
    fun `이름이_2자_이하면_검증되지않음`() {
        val userName = UserName.of("이")

        //then
        assert(userName is UserName.InValid)
        assert(userName.getInvalidType() == UserName.InValid.Type.LENGTH)
    }

    @Test
    fun `이름이_6자_이상이면_검증되지않음`() {
        val userName = UserName.of("이용우입니다")

        //then
        assert(userName is UserName.InValid)
        assert(userName.getInvalidType() == UserName.InValid.Type.LENGTH)
    }

    @Test
    fun `이름에_숫자나_기호가_포함되면_검증되지않음`() {
        val userName = UserName.of("2용우!")

        //then
        assert(userName is UserName.InValid)
        assert(userName.getInvalidType() == UserName.InValid.Type.FORMAT)
    }
}
