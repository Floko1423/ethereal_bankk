package com.bnp.ethereal_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//import com.bnp.ethereal_bank.configuration.CorsFilter;

//import com.bnp.ethereal_bank.configuration.CorsFilter;



@SpringBootApplication
public class EtherealBankApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EtherealBankApplication.class, args);
	}

    // @Bean
    // FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
    //     FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
    //     registration.setFilter(new CorsFilter());
    //     registration.addUrlPatterns("/*");
    //     registration.setName("corsFilter");
    //     registration.setOrder(1);
    //     return registration;
    // }

}
