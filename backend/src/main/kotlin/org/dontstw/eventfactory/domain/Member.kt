package org.dontstw.eventfactory.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "MEMBER")
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "MEMBER_ID")
        val id: Long,

        @Column(name = "MEMBER_ROLE")
        @Enumerated(EnumType.STRING)
        val role: ROLE,

        @Column(name = "MEMBER_NAME", nullable = false)
        var name: String,

        @Column(name = "MEMBER_PASSWORD", nullable = false)
        var password: String,

        @Column(name = "MEMBER_EMAIL", nullable = false)
        var email: String,

        @CreationTimestamp
        var createdTime: LocalDateTime,

        @UpdateTimestamp
        var updateTime: LocalDateTime
)