package com.folaukaveinga.springboot.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.springboot.domain.Attachment;

@Service
public class AttachmentService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public Attachment save(Attachment attachment){
		
		return null;
	}
	
	public Attachment saveFile(MultipartFile file){
		Attachment attachment = new Attachment();
		attachment.setName(file.getOriginalFilename());
		try {
			attachment.setFile(file.getBytes());
		} catch (IOException e) {
			log.warn("IOException, msg: {}",e.getMessage());
		}
		log.info(attachment.toString());
		return attachment;
	}
	
	public boolean saveList(List<Attachment> attachments){
		attachments.forEach((attachment)->{
			this.save(attachment);
		});
		return true;
	}
}
