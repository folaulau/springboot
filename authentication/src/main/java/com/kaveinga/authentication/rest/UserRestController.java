package com.kaveinga.authentication.rest;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private DefaultTokenServices tokenService;

	@GetMapping("/principal")
	public Principal user(HttpServletRequest request) {
		log.info("getting principal");
		Principal principal = request.getUserPrincipal();
		log.info("principal name: " + principal.getName());
		log.info(principal.toString());
		return principal;
	}

	@RequestMapping(value = "/token/revoke", method = RequestMethod.GET)
	public ResponseEntity<Boolean> revokeAccessToken(@RequestParam(name = "access_token") String accessToken) {
		log.info("revoke access token: " + accessToken);
		try {
			OAuth2AccessToken authAccessToken = tokenStore.readAccessToken(accessToken);
			if (authAccessToken != null) {
				log.info("token expired: " + authAccessToken.isExpired());

				log.info("expiration date: " + authAccessToken.getExpiration());

				tokenStore.removeAccessToken(authAccessToken);
			}

			OAuth2RefreshToken authRefreshToken = authAccessToken.getRefreshToken();

			if (authRefreshToken != null) {
				log.info("authRefreshToken value: " + authRefreshToken.getValue());
				tokenStore.removeRefreshToken(authRefreshToken);
			}

			boolean result = tokenService.revokeToken(authAccessToken.getValue());

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception, revokeAccessToken -> msg: " + e.getMessage());
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

	}

	// @RequestMapping(value = "/verify", method = RequestMethod.GET)
	// public ResponseEntity<Boolean> verify(@RequestParam(name =
	// "access_token") String accessToken) {
	// System.out.println("verify access token: "+accessToken);
	// try {
	// Token token = tokenService.verifyToken(accessToken);
	//
	// if(token!=null){
	// System.out.println("token key: "+token.getKey());
	// System.out.println("token creation time: "+new
	// Date(token.getKeyCreationTime()));
	// System.out.println("token ExtendedInformation:
	// "+token.getExtendedInformation());
	// return new ResponseEntity<>(true, HttpStatus.OK);
	// }else{
	// System.out.println("token is null");
	// return new ResponseEntity<>(false, HttpStatus.OK);
	// }
	//
	// } catch (Exception e) {
	// System.out.println("Exception, verify -> msg: "+e.getMessage());
	// return new ResponseEntity<>(false, HttpStatus.OK);
	// }
	//
	// }
}
