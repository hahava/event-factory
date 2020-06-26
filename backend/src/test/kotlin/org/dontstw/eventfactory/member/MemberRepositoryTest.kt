package org.dontstw.eventfactory.member

import org.dontstw.eventfactory.domain.Member
import org.dontstw.eventfactory.domain.ROLE
import org.dontstw.eventfactory.repository.MemberRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun `member select test`() {
        // given
        val USER_ID: Long = 1L

        // when
        val member = memberRepository.findById(USER_ID)

        // then
        member.ifPresent(Consumer { member -> assertEquals(member, "hahava") })
    }

    @Test
    @Rollback
    fun `member insert test`() {
        // given
        val member = Member(name = "MJ", role = ROLE.ADMIN, password = "{noop}1234", email = "MJ@ibk.com")

        // when
        val savedMember = memberRepository.save(member)

        // then
        assertNotNull(savedMember)
    }
}