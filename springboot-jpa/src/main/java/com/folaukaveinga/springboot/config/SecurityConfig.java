package com.folaukaveinga.springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.folaukaveinga.springboot.security.CustomAuthenticationFilter;

/**
 * To enable Authentication Filter do the following <br>
 * 1. Wire bean of CustomAuthenticationFilter into this class <br>
 * 2. Add bean as the authentication filter
 * 
 * @author folaukaveinga
 * 
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private CustomAuthenticationFilter customAuthenticationFilter;

	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() {
		return new CustomAuthenticationFilter();
	}

	@Bean
	public RegistrationBean jwtAuthFilterRegister(CustomAuthenticationFilter customAuthenticationFilter) {
		FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<CustomAuthenticationFilter>(
				customAuthenticationFilter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

	// @Autowired
	// private CustomAcccessDeniedHandler customAcccessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				//.antMatchers("/**").permitAll()
				.anyRequest().permitAll();

		http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// handler access denied calls
		// http.exceptionHandling().accessDeniedHandler(customAcccessDeniedHandler);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		//.antMatchers("/**")
		.antMatchers("/actuator/**");
	}
}
