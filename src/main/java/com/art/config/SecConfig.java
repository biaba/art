package com.art.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().and()
		.headers().httpStrictTransportSecurity().disable().and()

				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/string").permitAll()
				.antMatchers("/jsp").permitAll()
				.antMatchers("/advancedSearch").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/add/image/favourite").permitAll()
				.antMatchers("/remove/image/favourite").permitAll()
				.antMatchers("/check/**").permitAll()
				.antMatchers( "/favicon.ico").permitAll()
				.antMatchers("/search/**").permitAll()
				.antMatchers("/users/authenticate").permitAll()
				.anyRequest().authenticated().and().httpBasic();
	}	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
