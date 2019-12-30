package org.dontstw.eventfactory.repository

import org.dontstw.eventfactory.domain.Member
import org.junit.jupiter.api.Test

class MemberDTOTest {

    @Test
    fun `member dto test`() {
        var sampleMember = Member("hahava", "1234", "kalin")
        print(sampleMember);
    }
}