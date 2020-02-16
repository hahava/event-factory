package org.dontstw.eventfactory.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService? {
        val user: UserDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build()

        val admin: UserDetails = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build()
        return MapReactiveUserDetailsService(user, admin)
    }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        http
                .authorizeExchange { exchanges: AuthorizeExchangeSpec ->
                    exchanges.pathMatchers("/admin/**").hasRole("ADMIN")
                            .pathMatchers("/api/**").permitAll()
                            .anyExchange().authenticated()

                }
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
        return http.build()
    }
}
