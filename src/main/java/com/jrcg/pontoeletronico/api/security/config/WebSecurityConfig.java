/*package com.jrcg.pontoeletronico.api.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity.csrf().disable().exceptionHandling()
	    .authenticationEntryPoint(unauthorizedHandler)
	    .and().sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	    .authorizeRequests()
	    .antMatchers("/auth/**", "/api/cadastrar-pj", "/api/cadastrar-pf", "/v2/api-docs", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**")
	    .permitAll().anyRequest().authenticated();		
	}
}
*/