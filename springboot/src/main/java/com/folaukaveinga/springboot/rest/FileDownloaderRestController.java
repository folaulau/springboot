package com.folaukaveinga.springboot.rest;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.folaukaveinga.springboot.domain.Attachment;
import com.folaukaveinga.springboot.domain.Candidate;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.jms.Mail;
import com.folaukaveinga.springboot.jms.SenderService;
import com.folaukaveinga.springboot.service.AttachmentService;


@RestController
@RequestMapping("api/download")
public class FileDownloaderRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// @Value("${spring.http.multipart.location}")
	
	// inside resource directory
	private String tmpFolder = "tmp";
	
	@RequestMapping(value={"/{filename:.+}","/{filename:.+}/"}, method=RequestMethod.GET)
	public void get(@PathVariable("filename") String filename, HttpServletResponse response){
		log.info("file to download: {}", tmpFolder+"/"+filename);
		if(filename!=null){
			try {
				ClassPathResource fileResource = new ClassPathResource(tmpFolder+"/"+filename);
				if(fileResource.exists()==false){
					log.error("File, {}, does not exist", filename);
					throw new RuntimeException("File not found");
				}
				response.setContentType("application/xml");
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
				response.setHeader("Content-Length", String.valueOf(fileResource.contentLength()));
				FileCopyUtils.copy(fileResource.getInputStream(), response.getOutputStream());
				log.info(filename+" downloaded");
			} catch (IOException e) {
				log.error("IOException, msg: " + e.getLocalizedMessage());
			}
		}else{
			log.warn("File couldn't be downloaded from aws s3");
		}
	}
	
	
}
