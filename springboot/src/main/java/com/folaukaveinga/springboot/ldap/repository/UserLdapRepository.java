package com.folaukaveinga.springboot.ldap.repository;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.swing.plaf.synth.SynthSpinnerUI;

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
	public static final String BASE_DN = "dc=example,dc=com";
	
	@Autowired
	private LdapTemplate ldapTemplate;

	public User getUserByDn(String uid) {
		try {
			return ldapTemplate.lookup("uid="+uid+",ou=Users", new PersonAttributesMapper());
		} catch (Exception e) {
			System.err.println("Exception, msg: " + e.getMessage());
			return new User();
		}

	}

	public List<User> getAllUsers() {
		return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
	}
	
	public List<User> getAllUsersByDeparment(String department) {
		return ldapTemplate.search(query().where("objectclass").is("person").and("departmentNumber").is(department), new PersonAttributesMapper());
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

	public boolean create(User user) {
		Name dn = buildDn(user);
		if(dn==null)
			return false;
		
		DirContextAdapter context = new DirContextAdapter(dn);
		context.setAttributeValues("objectclass",new String[] { "top", "person", "organizationalPerson", "inetOrgPerson" });

		context.setAttributeValue("cn", user.getDisplayName());
		context.setAttributeValue("mail", user.getEmail());
		context.setAttributeValue("uid", user.getUid());
		context.setAttributeValue("givenName", user.getFirstName());
		context.setAttributeValue("department", "1700");
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
	
	public boolean update(User user) {
		Name dn = buildDn(user);
		if(dn==null)
			return false;
		
		try {
			ModificationItem[] attrs = {new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail", user.getEmail()))};
			ldapTemplate.modifyAttributes(dn, attrs);
			return true;
		} catch (Exception e) {
			System.out.println("Exception, msg: "+e.getMessage());
			return false;
		}

	}

	public String digestSHA(String rawPassword) {
		LdapShaPasswordEncoder ldapShaPasswordEncoder = new LdapShaPasswordEncoder();
		String hashedPassword =  ldapShaPasswordEncoder.encodePassword(rawPassword, null);
		System.out.println("rawPassword: "+rawPassword+", hashedPassword: "+hashedPassword);
		return hashedPassword;
	}
	
	public boolean delete(String uid) {
		try {
			this.ldapTemplate.unbind(buildDn(new User(uid)));
			return true;
		} catch (Exception e) {
			System.out.println("Exception, msg: " + e.getMessage());
			return false;
		}

	}

	public boolean authenticate(String uid, String password) {
		try {
			
			// either way works
			ldapTemplate.authenticate(query().where("uid").is(uid), password);
			//return this.ldapTemplate.authenticate("", "(uid=" + uid + ")", digestSHA(password));
			return true;
		} catch (Exception e) {
			System.out.println("Exception, msg: " + e.getMessage());
			return false;
		}

	}

	
	// build distinguishedName name
	private Name buildDn(User user) {
		Name dn = null;
		try {
			// if base not set in config file then use : LdapNameBuilder.newInstance(BASE_DN)
			dn = LdapNameBuilder.newInstance()
					.add("ou", "Users")
					.add("uid", user.getUid())
					.build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dn;
	}

}
