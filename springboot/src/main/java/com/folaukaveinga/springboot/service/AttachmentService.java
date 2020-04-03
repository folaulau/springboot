package com.folaukaveinga.springboot.service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.springboot.domain.Attachment;
import com.folaukaveinga.springboot.domain.Candidate;

@Service
public class AttachmentService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public Attachment save(Attachment attachment){
		log.info(attachment.toString());
		return attachment;
	}
	
	public Attachment saveFile(MultipartFile multipartFile){
		Attachment attachment = new Attachment();
		attachment.setName(multipartFile.getOriginalFilename());
		attachment.setFile(multipartFile);
		log.info(attachment.toString());
		return attachment;
	}
	
	public Attachment saveFile(File file){
		Attachment attachment = new Attachment();
		attachment.setName(file.getName());
		attachment.setFile(file);
		log.info(attachment.toString());
		return attachment;
	}
	
	public List<Attachment> saveFiles(MultipartFile[] multipartFiles){
		List<Attachment> attachments = new ArrayList<>();
		for (int i = 0; i < multipartFiles.length; i++) {
			attachments.add(this.saveFile(multipartFiles[i]));
		}
		return attachments;
	}
	
	public Attachment saveCandidate(Candidate candidate){
		log.info(candidate.toString());
		return new Attachment(candidate.getName(),null);
	}
	
	public List<Attachment> saveMultipartFiles(List<MultipartFile> multipartFiles){
		List<Attachment> attachments = new ArrayList<>();
		for (int i = 0; i < multipartFiles.size(); i++) {
			attachments.add(this.saveFile(multipartFiles.get(i)));
		}
		return attachments;
	}
	
	public List<Attachment> saveFiles(List<File> files){
		List<Attachment> attachments = new ArrayList<>();
		for (int i = 0; i < files.size(); i++) {
			attachments.add(this.saveFile(files.get(i)));
		}
		return attachments;
	}
	
	public boolean saveList(List<Attachment> attachments){
		attachments.forEach((attachment)->{
			this.save(attachment);
		});
		return true;
	}
}
