package com.folaukaveinga.springboot.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Attachment implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private File file;
	
	public Attachment() {
		
	}

	public Attachment(String name, File file) {
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		this.file.renameTo(new File("newFile"));
	}
	
	public void setFile(MultipartFile multipartFile) {
		file = new File(multipartFile.getOriginalFilename());
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
		} catch (IOException e) {
			System.err.println("Error, msg: " + e.getMessage());
		}
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
		return "Attachment [name=" + name + ", filename=" + file.getName() +", filename=" + file.length() + "]";
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
