package com.folaukaveinga.springboot.domain;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Form {
	private List<FormField> fields;
	
	public Form() {
		super();
	}

	
	public Form(List<FormField> fields) {
		super();
		this.fields = fields;
	}




	public List<FormField> getFields() {
		return fields;
	}


	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}


	public String printAsJson(){
		ObjectMapper objMapper = new ObjectMapper();
		try {
			return objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "{}";
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}
}
