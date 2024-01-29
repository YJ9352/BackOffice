package com.teamsparta.backoffice.infra.security

import com.teamsparta.backoffice.domain.oauth.service.CustomUserDetailService
import com.teamsparta.backoffice.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
        private val jwtAuthenticationFilter: JwtAuthenticationFilter,
        private val authenticationEntrypoint: CustomAuthenticationEntrypoint,
        private val accessDeniedHandler: CustomAccessDeniedHandler,
        private val customUserDetailService: CustomUserDetailService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .httpBasic { it.disable() }
                .formLogin { it.disable() }
                .csrf { it.disable() }
                .oauth2Login {
                    it.userInfoEndpoint { u -> u.userService(customUserDetailService) }
                    it.defaultSuccessUrl("/auth/login")
                    it.failureUrl("/fail")
                }
                .authorizeHttpRequests {
                    it.requestMatchers(AntPathRequestMatcher("/users")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/auth/login")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/users/signup")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/users/login")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                            .anyRequest().authenticated()
                }
                // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 적용
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

//                .exceptionHandling {
//                    it.authenticationEntryPoint(authenticationEntrypoint)
//                    it.accessDeniedHandler(accessDeniedHandler)
//                }
                .build()
    }

}