package org.dontstw.eventfactory.security

import org.dontstw.eventfactory.domain.ROLE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var securityService: UserDetailsService

    @Autowired
    lateinit var environment: Environment

    val DEV_ENV = "dev"

    override fun configure(http: HttpSecurity?) {
        // admin auth
        http!!
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(ROLE.ADMIN.name)

        // default auth
        http
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()

        // login
        http
                .formLogin()

        // logout
        http
                .logout()
                .logoutUrl("/admin/logout")
                .invalidateHttpSession(true)
                .deleteCookies()

        if (isDevEnvironment()) {
            http
                    .authorizeRequests()
                    .antMatchers("/h2-console/**").hasAuthority(ROLE.ADMIN.name)

            http.csrf().disable().headers().frameOptions().sameOrigin()
        }
    }

    private fun isDevEnvironment(): Boolean = DEV_ENV == environment.activeProfiles[0]


    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(securityService)
                .passwordEncoder(passWordEncoder())
    }

    @Bean
    fun passWordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

}