package com.folaukaveinga.springboot.ldap.repository;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import com.folaukaveinga.springboot.domain.User;

@Repository
public class UserLdapRepository {

	@Autowired
	private LdapTemplate ldapTemplate;

	public User getUserByDn(String dn) {
		return ldapTemplate.lookup(dn, new PersonAttributesMapper());
	}

	public List<User> getAllUsers() {
		return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
	}

	private class PersonAttributesMapper implements AttributesMapper<User> {
		public User mapFromAttributes(Attributes attrs) {
			User user = new User();
			
			try {
				user.setDisplayName(getValue(attrs.get("cn")));
				user.setLastName(getValue(attrs.get("sn")));
				user.setUid(getValue(attrs.get("uid")));
				
				user.setDisplayName(getValue(attrs.get("displayName")));
				user.setFirstName(getValue(attrs.get("givenName")));
				user.setEmail(getValue(attrs.get("mail")));
				
			} catch (Exception e) {
				System.err.println("Exception, msg: "+e.getMessage());
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
	
	public boolean create(User user) {
		System.out.println(user.toString());
		Name dn = null;
		try {
			dn = LdapNameBuilder
				      .newInstance()
				      .add("ou", "people")
				      .add("cn", user.getEmail())
				      .build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	    
	    DirContextAdapter context = new DirContextAdapter(dn);
	    context.setAttributeValues(
	      "objectclass", 
	      new String[] 
	        { "top", 
	          "person", 
	          "organizationalPerson", 
	          "inetOrgPerson" });
	    context.setAttributeValue("cn", user.getEmail());
	    context.setAttributeValue("mail", user.getEmail());
	    context.setAttributeValue("uid", user.getUid());
	    context.setAttributeValue("givenName", user.getFirstName());
	    context.setAttributeValue("sn", user.getLastName());
	    context.setAttributeValue("displayName", user.getDisplayName());
	    context.setAttributeValue("mail", user.getEmail());
	    context.setAttributeValue("userPassword", digestSHA(user.getPassword()));
	    
	    try {
	    		ldapTemplate.bind(context);
	    		return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	    
	}
	
	public String digestSHA(String password) {
		LdapShaPasswordEncoder ldapShaPasswordEncoder=new LdapShaPasswordEncoder();
		return ldapShaPasswordEncoder.encodePassword(password, null);
	}
	

}
