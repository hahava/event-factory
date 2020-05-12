package org.dontstw.eventfactory.config

import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class SecurityConfigTest {
    @Test
    fun `bcrypt_encoding_test`() {
        val bCryptPasswordEncoder = BCryptPasswordEncoder()
        val password = "password"
        val encodedPassword = "\$2a\$10\$dl72NTaVQNRpL1nPR1rBXOSsOZD73qLPc2vP38wMA5gcUFzj0LGkq"
        
        assertSame(bCryptPasswordEncoder.matches(password, encodedPassword), true)
    }
}