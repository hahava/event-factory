package org.dontstw.eventfactory.repository

import org.dontstw.eventfactory.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun findByName(memberName: String): Member
}