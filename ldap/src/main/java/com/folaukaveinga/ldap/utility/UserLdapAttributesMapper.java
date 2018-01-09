package com.folaukaveinga.ldap.utility;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;

import com.folaukaveinga.ldap.domain.LdapUser;


public class UserLdapAttributesMapper implements AttributesMapper<LdapUser> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public LdapUser mapFromAttributes(Attributes attrs) {
		LdapUser user = new LdapUser();
		try {

			// First Name
			Attribute attr = attrs.get("givenname");
			user.setLastName(this.getValue(attr));
			// Last Name
			attr = attrs.get("sn");
			user.setLastName(this.getValue(attr));
			
			// Common name
			attr = attrs.get("cn");
			user.setCn(this.getValue(attr));
			
			// id
			attr = attrs.get("samaccountname");
			user.setId(this.getValue(attr));
			
			// distinguishedName
			attr = attrs.get("distinguishedname");
			user.setDistinguishedName(this.getValue(attr));

			// Display Name
			attr = attrs.get("displayName");
			user.setDisplayName(this.getValue(attr));
			
			// Display Name
			attr = attrs.get("department");
			user.setDepartment(this.getValue(attr));
			
			// Email
			attr = attrs.get("mail");
			user.setEmail(this.getValue(attr));
			
			// Username
			attr = attrs.get("userPrincipalName");
			user.setLoginName(this.getValue(attr));

			// ipPhone
			attr = attrs.get("ipPhone");
			user.setIpPhone(this.getValue(attr));

			final List<String> directReports = new ArrayList<String>();
			try {
				final Enumeration en = attrs.get("directreports").getAll();
				final int nameOfUser = 0;
				while (en.hasMoreElements()) {
					final String temp = en.nextElement().toString().split(",")[nameOfUser];
					final String name = temp.substring(3, temp.length());
					directReports.add(name);
				}
				user.setDirectReports(directReports);
			} catch (final NullPointerException e) {
			}

		} catch (Exception e) {
			System.err.println("Exception, msg: " + e.getMessage());
		}

		return user;
	}

	private String getValue(Attribute attr) {
		try {
			return attr.get().toString();
		} catch (Exception e) {
			return "";
		}
	}
}
