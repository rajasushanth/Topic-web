package com.starkinc.wtopic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author RajaSushanth
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/css/**", "/js/**", "/img/**", "/less/**", "/scss/**", "/vendor/**", "/mail/**", "/fonts/**").permitAll()
		.antMatchers("/actuator/**").permitAll()
		.antMatchers(POST, "/login").permitAll()
		.antMatchers(POST, "/signUp").permitAll()
		.antMatchers(GET, "/recover").permitAll()
		.antMatchers(POST, "/recover/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(new LoginFilter("/login", authenticationManager()) , UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.logout().logoutSuccessUrl("/") .invalidateHttpSession(true).clearAuthentication(true).deleteCookies("TOPIC_SESSSION_ID")
		.and()
		.httpBasic().disable();
	}
	
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
