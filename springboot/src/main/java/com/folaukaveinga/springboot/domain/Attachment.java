package com.folaukaveinga.springboot.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Attachment implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private byte[] file;
	
	public Attachment() {
		
	}

	public Attachment(String name, byte[] file) {
		super();
		this.name = name;
		this.file = file;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFile() {
		return file;
	}

	public File generateFile() throws IOException{
		File f = new File(name);
		FileOutputStream stream = new FileOutputStream (f);
		stream.write(file);
		return f;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static Attachment fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, Attachment.class);
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
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
