package org.dontstw.eventfactory.security

import org.dontstw.eventfactory.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class SecurityService : UserDetailsService {

    @Autowired
    lateinit var memberRepository: MemberRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByName(username)
        return User(member.name, member.password, getMemberRoles(member.role.name))
    }

    private fun getMemberRoles(role: String): Set<SimpleGrantedAuthority> {
        return setOf(SimpleGrantedAuthority(role))
    }
}