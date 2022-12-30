package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class GraphQlSecurityConfig {

	@Bean
	public UserDetailsService userDetails() {
		UserDetails admin = User.builder().username("admin").password("password").roles("ADMIN","USER")
				.build();
		UserDetails adminOnly = User.builder().username("admin1").password("password").roles("ADMIN")
				.build();
		UserDetails user = User.builder().username("user").password("password").roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin,adminOnly,user);
	}
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeExchange().anyExchange().authenticated().and().httpBasic().and().formLogin();
		return http.csrf().disable()
				.authorizeRequests().anyRequest().authenticated().and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(withDefaults()).build();
	}

}
