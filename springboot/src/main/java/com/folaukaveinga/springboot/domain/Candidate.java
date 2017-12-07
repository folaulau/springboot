package com.folaukaveinga.springboot.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Candidate implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private MultipartFile file;
	
	
	private List<MultipartFile> attachments;
	
	public Candidate() {
		
	}

	public Candidate(String name, MultipartFile file) {
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public File generateActualFile() {
		File newFile = new File(file.getOriginalFilename());
		try {
			newFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			System.err.println("Error, msg: " + e.getMessage());
		}
		return newFile;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static Candidate fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, Candidate.class);
	}



	public String toJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}

	

//	@Override
//	public String toString() {
//		System.out.println("name: "+name);
//		if(file!=null){
//			System.out.println("filename: "+file.getOriginalFilename());
//		}else{
//			System.out.println("file is empty");
//		}
//		return "Attachment [name=" + name + ", filename=" + file.getOriginalFilename()+", file size=" + file.getSize() + "]";
//	}
	
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

	public List<MultipartFile> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<MultipartFile> attachments) {
		this.attachments = attachments;
	}

}
