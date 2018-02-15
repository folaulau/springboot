package com.kaveinga.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kaveinga.authentication.security.CustomAuthenticationSuccessHandler;
import com.kaveinga.authentication.security.CustomLogoutSuccessHandler;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

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
                		.successHandler(authenticationSuccessHandler())
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
			.antMatchers("/node_modules/**");

	}


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("peter@gmail.com")
                .password("peter")
                .roles("INSTAGRAM","FACEBOOK");
    }

}