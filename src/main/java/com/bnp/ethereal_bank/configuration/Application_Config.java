package com.bnp.ethereal_bank.configuration;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.repository.Client_Repository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Application_Config {

    
    private  Client_Repository repository;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found")); // get user from db -> fazer com
                                                                                     // outros metodos

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());  // criar um bean 
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        
    return authConfig.getAuthenticationManager();

    }
}
