package com.folaukaveinga.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.folaukaveinga.api.security.AuthenticationFilter;
import com.folaukaveinga.api.security.LoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationFilter authFilter;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	// this bean makes sure a request is not duplicated
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(authFilter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}
	
	@Bean
	public AntPathRequestMatcher loginAntPathRequestMatcher() {
		return new AntPathRequestMatcher("/api/login");
	}
	
	@Bean
	public LoginFilter loginFilter() {
		return new LoginFilter(loginAntPathRequestMatcher(), authenticationManager);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/api/login").permitAll()
					.antMatchers("/api/public", "/api/public/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(loginFilter(),UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
