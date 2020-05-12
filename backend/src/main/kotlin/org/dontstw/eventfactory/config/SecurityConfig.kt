package org.dontstw.eventfactory.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var dataSource: DataSource

    override fun configure(http: HttpSecurity?) {
        // admin auth
        http!!
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .and()
                .logout()
                .logoutUrl("/admin/logout")
                .invalidateHttpSession(true)
                .deleteCookies()

        // default auth
        http!!
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()

        http!!
                .authorizeRequests()
                .antMatchers("/h2-console/*")
                .permitAll()

        http!!.formLogin()
        http!!.csrf().disable()
        http!!.headers().frameOptions().disable()

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passWordEncoder())
    }

    @Bean
    fun passWordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}