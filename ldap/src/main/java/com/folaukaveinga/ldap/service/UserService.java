package com.folaukaveinga.ldap.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
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
	
	@Value("${spring.ldap.base}")
	private String LDAP_BASE;

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
			List<LdapUser> users = ldapTemplate.search(query().where("objectclass").is("person").and("samaccountname").is(uid), new UserLdapAttributesMapper());
			return (users!=null && users.size()>0) ? users.get(0) : new LdapUser();
		} catch (Exception e) {
			log.error("Exception, msg: "+e.getMessage());
			return new LdapUser();
		}
	}
	
	public List<LdapUser> getUsersByDepartment(final String department) {
		try {
			return ldapTemplate.search(query().where("objectclass").is("person").and("department").is(department), new UserLdapAttributesMapper());
		} catch (Exception e) {
			log.error("Exception, msg: "+e.getMessage());
			return new ArrayList<LdapUser>();
		}
	}
	
	public List<LdapUser> getAllUsers() {
		try {
			return ldapTemplate.search(query().where("objectclass").is("person"), new UserLdapAttributesMapper());
		} catch (Exception e) {
			log.error("Exception, msg: "+e.getMessage());
			return new ArrayList<LdapUser>();
		}
	}
	

	public boolean changeEmail(String uid, String newEmail) {
		return updateEmail(this.getUser(uid), newEmail);
	}
	
	private Name buildDn(LdapUser ldapUser) {
		Name dn = null;
		try {
			dn = LdapNameBuilder.newInstance()
					.add("CN", ldapUser.getCn())
					.build();
		} catch (Exception e) {
			log.error("buildDn Exception, msg: "+e.getMessage());
		}
		return dn;
	}
	
	public boolean updateEmail(LdapUser ldapUser, String newEmail) {
		Name dn = buildDn(ldapUser);
		if(dn==null)
			return false;
		
		try {
			ModificationItem[] attrs = {new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail", newEmail))};
			ldapTemplate.modifyAttributes(dn, attrs);
			return true;
		} catch (Exception e) {
			log.error("updateEmail Exception, msg: {}",e.getMessage());
			return false;
		}
	}
	
	public boolean create(LdapUser ldapUser){
		Name dn = buildDn(ldapUser);
		if(dn==null)
			return false;
		
		DirContextAdapter context = new DirContextAdapter(dn);
		context.setAttributeValues("objectclass",new String[] { "user", "top", "person", "organizationalPerson" });

		context.setAttributeValue("cn", ldapUser.getDisplayName());
		context.setAttributeValue("mail", ldapUser.getEmail());
		context.setAttributeValue("samaccountname", ldapUser.getId());
		context.setAttributeValue("uid", ldapUser.getId());
		context.setAttributeValue("givenName", ldapUser.getFirstName());
		context.setAttributeValue("department", ldapUser.getDepartment());
		context.setAttributeValue("sn", ldapUser.getLastName());
		context.setAttributeValue("displayName", ldapUser.getDisplayName());
		context.setAttributeValue("userPrincipalName", ldapUser.getId()+domainName);
		context.setAttributeValue("userPassword", digestSHA(ldapUser.getPassword()));

		try {
			ldapTemplate.bind(context);
			return true;
		} catch (Exception e) {
			log.error("create Exception, msg: "+e.getMessage());
			return false;
		}
	}
	
	private String digestSHA(String rawPassword) {
		LdapShaPasswordEncoder ldapShaPasswordEncoder = new LdapShaPasswordEncoder();
		String hashedPassword =  ldapShaPasswordEncoder.encodePassword(rawPassword, null);
		System.out.println("rawPassword: "+rawPassword+", hashedPassword: "+hashedPassword);
		return hashedPassword;
	}
}
