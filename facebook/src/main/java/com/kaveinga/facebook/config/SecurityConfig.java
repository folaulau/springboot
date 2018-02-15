package com.kaveinga.facebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextListener;


@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.antMatcher("/**")
				.authorizeRequests()
					.antMatchers("/login**","/public").permitAll()
					.antMatchers("/**")
						.hasRole("FACEBOOK")
				.anyRequest()
					.authenticated()
				.and()
					.exceptionHandling().accessDeniedPage("/access-denied")
				.and()
					.logout()
					.deleteCookies("JSESSIONID", "CSRF-TOKEN", "FBUISESSION")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessHandler(customLogoutSuccessHandler)
					.permitAll()
				
				.and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}
