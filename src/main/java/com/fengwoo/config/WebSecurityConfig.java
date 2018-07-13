package com.fengwoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by wgu on 2018/07/13 10:12.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/","/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/chat")
			.permitAll()
			.and()
			.logout()
			.permitAll();
	}
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth
			.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
			.withUser("wang").password(new BCryptPasswordEncoder().encode("wang")).roles("USER")
			.and()
			.withUser("guan").password(new BCryptPasswordEncoder().encode("guan")).roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/static/**");
	}
}
