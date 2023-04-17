package com.gbsw.dormitory.management.dormitorymanagementserver.v1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {



    private final JwtProvider jwtTokenProvider;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers("/api/v1/user/test").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()

                .and().logout().permitAll()

                .and()

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();


    }


}