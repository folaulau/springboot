package com.folaukaveinga.springboot.rest;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.folaukaveinga.springboot.domain.Attachment;
import com.folaukaveinga.springboot.jms.Mail;
import com.folaukaveinga.springboot.jms.SenderService;
import com.folaukaveinga.springboot.service.AttachmentService;


@RestController
@RequestMapping("api/attachments")
public class AttachmentRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(value={"/{id}","/{id}/"}, method=RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Attachment> get(@PathVariable("id") int id){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/",""}, method=RequestMethod.POST)
	public ResponseEntity<Attachment> save(@RequestParam(value="file") MultipartFile file){
		log.info("uploading file to server. Filename: {}, File size: {}", file.getOriginalFilename(), file.getSize());
		return new ResponseEntity<>(attachmentService.saveFile(file), HttpStatus.OK);
	}
	//MultipartHttpServletRequest request
	@RequestMapping(value={"/multiple","/multiple/"}, method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Attachment>> saveMultiple(@RequestParam(value="files") List <MultipartFile> files){
		
		log.info("uploading files to server. upload size: {}", files.size());
		return new ResponseEntity<>(attachmentService.saveMultipartFiles(files), HttpStatus.OK);
	}
}
