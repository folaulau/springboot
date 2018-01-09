package com.folaukaveinga.ldap.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.folaukaveinga.ldap.domain.LdapUser;
import com.folaukaveinga.ldap.utility.UserLdapAttributesMapper;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.ldap.userPrincipalName}")
	private String userPrincipalName;

	@Value("${spring.ldap.domainName}")
	private String domainName;

	@Autowired
	private LdapTemplate ldapTemplate;

	public boolean login(String uid, String password) {
		try {
			log.info("logging in uid: {}, password: {}", uid, password);
			ldapTemplate.authenticate(query().where("objectclass").is("person").and("userPrincipalName").is(uid + domainName), password);
			log.info("login credentials are valid!!!");
			return true;
		} catch (Exception e) {
			log.info("Exception, msg: " + e.getMessage());
			log.info("login credentials are invalid!!!");
			return false;
		}
	}
	
	public LdapUser getUser(final String uid) {
		try {
			return ldapTemplate.search(query().where("objectclass").is("person").and("userPrincipalName").is(uid+domainName), new UserLdapAttributesMapper()).get(0);
		} catch (Exception e) {
			log.error("Exception, msg: "+e.getMessage());
			return new LdapUser();
		}
	}
}
