package com.lovemesomecoding.index.doctor;

import java.io.Serializable;
import java.util.Set;

public class CareRateIndex implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sidecarCode;

	private Double averageProviderRate;

	// from care service
	private Set<String> careSpecialties;

	public CareRateIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSidecarCode() {
		return sidecarCode;
	}

	public void setSidecarCode(String sidecarCode) {
		this.sidecarCode = sidecarCode;
	}

	public Double getAverageProviderRate() {
		return averageProviderRate;
	}

	public void setAverageProviderRate(Double averageProviderRate) {
		this.averageProviderRate = averageProviderRate;
	}

	public Set<String> getCareSpecialties() {
		return careSpecialties;
	}

	public void setCareSpecialties(Set<String> careSpecialties) {
		this.careSpecialties = careSpecialties;
	}

}
