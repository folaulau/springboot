package com.folaukaveinga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.folaukaveinga.security.CustomAcccessDeniedHandler;
import com.folaukaveinga.security.CustomAuthenticationFilter;
import com.folaukaveinga.security.CustomAuthenticationProvider;
import com.folaukaveinga.security.CustomLoginFilter;
import com.folaukaveinga.security.CustomLogoutHandler;
import com.folaukaveinga.security.CustomLogoutSuccessHandler;
import com.folaukaveinga.utils.PathConstantUtil;

/**
 * Configure Security
 * @author fkaveinga
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private CustomLogoutHandler customLogoutHandler;
	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
	private CustomAcccessDeniedHandler customAcccessDeniedHandler;
	
	@Bean
	public CustomLoginFilter customUsernamePassworAuthenticationFilter() throws Exception {
		return new CustomLoginFilter(PathConstantUtil.LOGIN_URL,authenticationManagerBean());
	}
	
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
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// rest call rules
		http
			.cors().and().csrf().disable()
			.authorizeRequests()
				.antMatchers(PathConstantUtil.PING_URL).permitAll()
				.antMatchers(PathConstantUtil.LOGIN_URL).permitAll()
				.antMatchers(PathConstantUtil.SIGNUP_URL).permitAll()
				.anyRequest().permitAll();
					
		// logout
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher(PathConstantUtil.LOGOUT_URL))
			.addLogoutHandler(customLogoutHandler)
			.logoutSuccessHandler(customLogoutSuccessHandler);
		
		// filter
		http.addFilterBefore(customUsernamePassworAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
		
		http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// stateless
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// handler access denied calls
		http.exceptionHandling().accessDeniedHandler(customAcccessDeniedHandler);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(customAuthenticationProvider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(PathConstantUtil.SIGNUP_URL)
			.antMatchers(PathConstantUtil.LOGIN_URL)
			.antMatchers(PathConstantUtil.PING_URL)
			.antMatchers(PathConstantUtil.AUTH_TOKEN_URL)
			.antMatchers(PathConstantUtil.SWAGGER_DOC_URLS)
			.antMatchers("/actuator/**");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
	    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
	    methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
	    methodInvokingFactoryBean.setTargetMethod("setStrategyName");
	    methodInvokingFactoryBean.setArguments(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	    return methodInvokingFactoryBean;
	}
}
