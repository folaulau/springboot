package com.folaukaveinga.springboot.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.springboot.domain.Attachment;
import com.folaukaveinga.springboot.domain.Candidate;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.AttachmentService;

import io.swagger.annotations.Api;

@Api(value = "attachments", produces = "Rest API for attachments operations", tags = "Attachment Controller")
@RestController
@RequestMapping("api/attachments")
public class AttachmentRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Attachment> get(@PathVariable("id") int id) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Attachment> save(@RequestParam(value = "file") MultipartFile file) {
		log.info("uploading file to server. Filename: {}, File size: {}", file.getOriginalFilename(), file.getSize());
		return new ResponseEntity<>(attachmentService.saveFile(file), HttpStatus.OK);
	}

	/**
	 * 
	 * @param files
	 * @return
	 */
	@RequestMapping(value = {
			"/multiple" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Attachment>> saveMultiple(@RequestParam(value = "files") List<MultipartFile> files) {

		log.info("uploading files to server. upload size: {}", files.size());
		return new ResponseEntity<>(attachmentService.saveMultipartFiles(files), HttpStatus.OK);
	}

	// { "multipart/mixed", "multipart/form-data" }
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Attachment> saveAttachment(@RequestParam("user") String jsonUser,
			@RequestParam("file") MultipartFile multipartFile) {
		log.info("uploading attachment to server. File size: {}", multipartFile.getSize());
		log.info("json user: " + User.fromJson(jsonUser).toString());
		return new ResponseEntity<>(attachmentService.saveFile(multipartFile), HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/save-list" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Attachment>> saveAttachments(@RequestParam("user") String jsonUser,
			@RequestParam("files") List<MultipartFile> multipartFiles) {
		log.info("uploading attachments to server. size: {}", multipartFiles.size());
		log.info("json user: " + User.fromJson(jsonUser).toString());
		return new ResponseEntity<>(attachmentService.saveMultipartFiles(multipartFiles), HttpStatus.OK);
	}

	// @ModelAttribute
	@RequestMapping(value = { "/candidate" }, method = RequestMethod.POST)
	public ResponseEntity<Attachment> saveCandidate(@Valid @ModelAttribute Candidate candidate, BindingResult result) {
		result.getAllErrors().forEach((e) -> {
			log.info("name: {}, msg: {}", e.getObjectName(), e.getDefaultMessage());
		});
		return new ResponseEntity<>(attachmentService.saveCandidate(candidate), HttpStatus.OK);
	}
}