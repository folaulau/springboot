package com.lovemesomecoding.index.doctor;

public enum DoctorIndexName {

	DOCTOR("doctors"), SPECIALITY("doctor_specialities"), TAXONOMYMAPPER("doctor_taxonomies");

	String name;

	DoctorIndexName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
