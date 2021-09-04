package com.example.boot.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import ch.qos.logback.classic.Logger;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		authenticationSuccessHandler.setTargetUrlParameter("redirectTo");
		authenticationSuccessHandler.setDefaultTargetUrl("/");
		http.authorizeRequests().antMatchers("/assets/**").permitAll()
		                        .antMatchers("login").permitAll()
		                        .anyRequest().authenticated()
		                        .and().formLogin().loginPage("/login")
		                        .successHandler(authenticationSuccessHandler)
		                        .and().logout().logoutUrl("logout")
		                        .and().httpBasic()
		                        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		                        .ignoringAntMatchers("/instances","/actuator/**");
	}
}
