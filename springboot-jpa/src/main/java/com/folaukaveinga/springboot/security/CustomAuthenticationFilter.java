package com.folaukaveinga.springboot.security;

import java.io.IOException;
import java.time.ZoneId;
import java.time.zone.ZoneRules;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.folaukaveinga.springboot.utility.Factory;

public class CustomAuthenticationFilter extends OncePerRequestFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handle token missing error <br>
	 * Handle cached user not found error <br>
	 * Handle user with no roles <br>
	 * If all goes well, let request continue down the line
	 * 
	 * @author fkaveinga
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("\n\n");
		log.info("doFilterInternal(...)");
		
		Calendar calenda = Calendar.getInstance(request.getLocale());
		log.info("calenda: {}",Factory.getObjectMapper().writeValueAsString(calenda));
		TimeZone timeZone = calenda.getTimeZone();
		log.info("timezone: {}",Factory.getObjectMapper().writeValueAsString(timeZone));
		log.info("raw off set: {}", timeZone.getRawOffset());
		ZoneId zoneId = timeZone.toZoneId();
		
		log.info("zone id: {}", zoneId);
		log.info("zone id: {}", zoneId.getId());
		log.info("zone rules: {}", zoneId.getRules());
		log.info("zone available zone ids: {}", Factory.getObjectMapper().writeValueAsString(zoneId.getAvailableZoneIds()));
		log.info("zone displayname: {}", timeZone.getDisplayName());
		log.info("zone name with locale: {}", timeZone.getDisplayName(request.getLocale()));

		TimeZone.setDefault(TimeZone.getTimeZone(zoneId));
		
		System.out.println("time: "+Factory.getSidecarDateForm().format(new Date()));

		System.out.println("\n\n");
		filterChain.doFilter(request, response);
	}
}
