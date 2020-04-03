package com.folaukaveinga.springboot.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.springboot.domain.Attachment;
import com.folaukaveinga.springboot.domain.JsonData;
import com.folaukaveinga.springboot.service.AttachmentService;

import io.swagger.annotations.Api;

@Api(value = "files", produces = "Rest API for files operations", tags = "File Upload Controller")
@RestController
@RequestMapping("api/files")
public class FileUploadRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * Upload a single file
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Attachment> uploadSingleFile(@RequestPart(value = "file") MultipartFile file) {
		log.info("uploading file to server. Filename: {}, File size: {}", file.getOriginalFilename(), file.getSize());
		return new ResponseEntity<>(attachmentService.saveFile(file), HttpStatus.OK);
	}

//	/**
//	 * Upload a single file with @RequestParam and is working
//	 */
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Attachment> uploadSingleFile(@RequestParam(value = "file") MultipartFile file) {
//		log.info("uploading file to server. Filename: {}, File size: {}", file.getOriginalFilename(), file.getSize());
//		return new ResponseEntity<>(attachmentService.saveFile(file), HttpStatus.OK);
//	}

	/**
	 * Upload multiple files
	 */
	@RequestMapping(value = {
			"/multiple" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Attachment>> saveMultiple(@RequestPart(value = "files") MultipartFile[] files) {

		log.info("uploading files to server. upload size: {}", files.length);
		return new ResponseEntity<>(attachmentService.saveMultipartFiles(files), HttpStatus.OK);
	}

//	/**
//	 * Upload multiple files with @RequestParam and is working
//	 */
//	@RequestMapping(value = {
//			"/multiple" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<List<Attachment>> saveMultiple(@RequestParam(value = "files") MultipartFile[] files) {
//
//		log.info("uploading files to server. upload size: {}", files.length);
//		return new ResponseEntity<>(attachmentService.saveMultipartFiles(files), HttpStatus.OK);
//	}

	/**
	 * Upload a single file with json data not working
	 */
	@RequestMapping(value = "/uploadWithData", method = RequestMethod.POST)
	public ResponseEntity<Attachment> uploadSingleFileWithJsonObject(@RequestParam(value = "file") MultipartFile file,
			@RequestParam JsonData jsonData) {
		log.info("uploading file to server. Filename: {}, File size: {}", file.getOriginalFilename(), file.getSize());
		log.info("jsonData={}", jsonData.toJson());
		return new ResponseEntity<>(attachmentService.saveFile(file), HttpStatus.OK);
	}
}
