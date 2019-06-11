package com.folaukaveinga.rest;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "coverages", produces = "Rest API for home operations", tags = "Home Rest Controller")
@RestController
public class HomeRestControler {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageSource messageSource;

	
	/**
	 * @RequestHeader - @RequestHeader String token. 
	 * @param token
	 * @return
	 */
	@ApiOperation(value = "Get API")
	@GetMapping("api")
	public ResponseEntity<String> home(@RequestParam("lang") String lang) {
		log.info("home");
		log.info("lang={}", lang);
		//log.info("Token: {}", token);
		
		Locale locale = LocaleContextHolder.getLocale();
		
		 log.info("Country: {}, Language; {}, Display Language: {}",
		 locale.getDisplayCountry(), locale.getLanguage(),
		 locale.getDisplayLanguage());
		
		log.info("Locale: {}",locale.toString());
		String message = messageSource.getMessage("welcome.message", new Object[] { "Angelo", "TCG"},locale);
		log.info("message: {}",message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
