package com.bnp.ethereal_bank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private final Jwt_Authentication_Filter jwtAuthFilter;

        private final AuthenticationProvider authenticationProvider;


    public SecurityConfig(Jwt_Authentication_Filter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http
         .csrf().disable()  // temos alguns endpoints que nao precisam de auth/token -> ex para criar account/login
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/auth/**") // ainda nao tenho endpoints neste authcontroller portanto estou a allow todos os metodos neste controller
        .permitAll() // todos os que nao precisam
        .anyRequest() // os outros precisam de ser autenticados
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // visto que é OncePerRequest
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  // quero este filtro antes de executar o filtro UserNamePasswordAuthFilter
        
        return http.build();

        .authorizeHttpRequests(auth -> auth
        .anyRequest()
        .authenticated()
        ).formLogin(withDefaults())
        .build();

    }

    

    


    // Other security configuration code
}