package org.dontstw.eventfactory.config

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

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
        http
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()

        http.formLogin()
        http.csrf().disable()
    }

    //TODO: It use memory auth but Should be replaced using jdbc.
    override fun configure(auth: AuthenticationManagerBuilder?) {
        // {noop} is meaning password not encoded
        auth!!.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN")
    }
}
