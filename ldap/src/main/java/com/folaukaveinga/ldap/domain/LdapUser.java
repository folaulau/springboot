package com.folaukaveinga.ldap.domain;

import java.util.List;

public class LdapUser implements Comparable<LdapUser> {

	private String id;
	private String cn;// common name
	private String firstName;
	private String lastName;
	private String email;
	private String displayName;
	private String distinguishedName;
	private String department;
	private String loginName;
	private String password;
	private String title;
	private String phone;
	private String type;
	private String ipPhone;// benefit or non benefit.
	private String joinDate;
	private List<String> directReports;

	public LdapUser() {
	}
	
	public LdapUser(String cn) {
		this.cn = cn;
	}

	public LdapUser(final String id, final String title, final String phone, final String email) {
		this.id = id;
		this.title = title;
		this.phone = phone;
		this.email = email;
	}
	
	public LdapUser(String firstName, String lastName,String department,String email,String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.email = email;
		this.password = password;
	}

	public LdapUser(final String id, final String cn, final String firstName, final String lastName, final String email, final String displayName,
			final String department, final String loginName, final String password, final String type, final String ipPhone, final String joinDate) {
		this.id = id;
		this.cn = cn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.displayName = displayName;
		this.department = department;
		this.loginName = loginName;
		this.password = password;
		this.type = type;
		this.ipPhone = ipPhone;
		this.joinDate = joinDate;
	}

	

	@Override
	public String toString() {
		return "UserLdap [id=" + id + ", cn=" + cn + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", displayName=" + displayName + ", distinguishedName="
				+ distinguishedName + ", department=" + department + ", loginName=" + loginName + ", password="
				+ password + ", title=" + title + ", phone=" + phone + ", type=" + type + ", ipPhone=" + ipPhone
				+ ", joinDate=" + joinDate + ", directReports=" + directReports + "]";
	}

	public LdapUser(final String id, final String cn, final String firstName, final String lastName, final String email,
			final String displayName, final String department, final String loginName, final String password, final String title, final String phone,
			final String type, final String ipPhone, final String joinDate) {
		super();
		this.id = id;
		this.cn = cn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.displayName = displayName;
		this.department = department;
		this.loginName = loginName;
		this.password = password;
		this.title = title;
		this.phone = phone;
		this.type = type;
		this.ipPhone = ipPhone;
		this.joinDate = joinDate;
	}

	public String getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(final String joinDate) {
		this.joinDate = joinDate;
	}

	public String getId() {
		return this.id;
	}


	public String getCn() {
		return this.cn;
	}

	public void setCn(final String cn) {
		this.cn = cn;
	}

	public String getTitle() {
		return this.title;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(final String department) {
		this.department = department;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(final String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getIpPhone() {
		return this.ipPhone;
	}

	public void setIpPhone(final String ipPhone) {
		this.ipPhone = ipPhone;
	}

	public List<String> getDirectReports() {
		return this.directReports;
	}

	public void setDirectReports(final List<String> directReports) {
		this.directReports = directReports;
	}

	@Override
	public int compareTo(final LdapUser obj) {
		return this.getCn().compareTo(obj.getCn());
	}
}
