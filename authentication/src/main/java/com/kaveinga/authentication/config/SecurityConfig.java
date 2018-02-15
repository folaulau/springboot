package com.kaveinga.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize", "/logout", "/resources/**", "/node_modules/**")
                .and()
                		.authorizeRequests()
                		.anyRequest()
                		.authenticated()
                .and()
                		.formLogin()
                		.loginPage("/login")
                    .permitAll()
                .and()
                		.logout()
                		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                		.logoutSuccessHandler(customLogoutSuccessHandler)
                		.permitAll();
    }
    
    @Override
	public void configure(final WebSecurity web) throws Exception {
		web
		.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/node_modules/**");

	}


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
                .inMemoryAuthentication()
                .withUser("peter@gmail.com")
                .password("peter")
                .roles("INSTAGRAM","FACEBOOK");
    }

}