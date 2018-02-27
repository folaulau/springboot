package com.folaukaveinga.springboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormField {
	private String fieldType;
	private String fieldQuestion;
	private String fieldHelperDescription;
	private String fieldAttribute;
	private String fieldRequired;
	private String fieldRequiredErrorMessage;
	
	public FormField() {
		super();
	}

	public FormField(String fieldQuestion, String fieldHelperDescription, String fieldAttribute, String fieldRequired,
			String fieldRequiredErrorMessage) {
		super();
		this.fieldQuestion = fieldQuestion;
		this.fieldHelperDescription = fieldHelperDescription;
		this.fieldAttribute = fieldAttribute;
		this.fieldRequired = fieldRequired;
		this.fieldRequiredErrorMessage = fieldRequiredErrorMessage;
	}
	
	public String getFieldHelperDescription() {
		return fieldHelperDescription;
	}

	public void setFieldHelperDescription(String fieldHelperDescription) {
		this.fieldHelperDescription = fieldHelperDescription;
	}
	
	public String getFieldAttribute() {
		return fieldAttribute;
	}

	public void setFieldAttribute(String fieldAttribute) {
		this.fieldAttribute = fieldAttribute;
	}
	
	public String getFieldRequired() {
		return fieldRequired;
	}

	public void setFieldRequired(String fieldRequired) {
		this.fieldRequired = fieldRequired;
	}

	public String getFieldRequiredErrorMessage() {
		return fieldRequiredErrorMessage;
	}

	public void setFieldRequiredErrorMessage(String fieldRequiredErrorMessage) {
		this.fieldRequiredErrorMessage = fieldRequiredErrorMessage;
	}
	
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public String getFieldQuestion() {
		return fieldQuestion;
	}

	public void setFieldQuestion(String fieldQuestion) {
		this.fieldQuestion = fieldQuestion;
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
