package com.svs.security.configs;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.svs.security.provider.CustomAuthenticationProvider;




@EnableWebSecurity
@Configuration
public class SecurityConfig{


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
	    http
	    .csrf().disable()
        .authorizeRequests()
		.antMatchers(
				"/checkId",
				"/boardRead",
				"/boardList",
				"/insert",
				"/js/**",
				"/img/**",
				"/css/**",
				"/error",
				"/boards/**"
        ).permitAll()
        .anyRequest().authenticated();

	    http
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .permitAll();
		
		return http.build();
		
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
}
