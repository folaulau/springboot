package com.folaukaveinga.springboot.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.springboot.domain.Attachment;
import com.folaukaveinga.springboot.domain.JsonData;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.AttachmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	 * Upload a single file with json data(only working with json file - json object
	 * inside) <br>
	 * https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-
	 * multipart-and-json-to-dto <br>
	 * 
	 * 
	 */
	@ApiOperation(value = "Upload With Json Object", notes = "Use FormData<br>var formData = new FormData(); <br>From frontend. Stringtify json object(user)")
	@RequestMapping(value = "/uploadWithJsonObject", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Attachment> uploadSingleFileWithJsonObject(@RequestPart(value = "file") MultipartFile file,
			@ApiParam(name = "user") @RequestPart(name = "user") JsonData jsonData) {
		log.info("uploading file to server. Filename: {}, File size: {}", file.getOriginalFilename(), file.getSize());
		log.info("jsonData={}", jsonData.toJson());
		return new ResponseEntity<>(attachmentService.saveFile(file), HttpStatus.OK);
	}

	/**
	 * Upload a single file with json data(only working with json file - json object
	 * inside) <br>
	 * https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-
	 * multipart-and-json-to-dto <br>
	 * 
	 * 
	 */
	@ApiOperation(value = "Upload Multiple Files With Json Object", notes = "Use FormData<br>var formData = new FormData(); <br>From frontend. Stringtify json object(user)")
	@RequestMapping(value = "/uploadMultipleFilesWithJsonObject", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Attachment>> uploadMultipleFilesWithJsonObject(
			@RequestPart(value = "files") List<MultipartFile> files,
			@ApiParam(name = "user") @RequestPart(name = "user") JsonData jsonData) {
		log.info("uploadMultipleFilesWithJsonObject File size: {}", files.size());
		log.info("jsonData={}", jsonData.toJson());
		return new ResponseEntity<>(attachmentService.saveMultipartFiles(files), HttpStatus.OK);
	}

	/**
	 * Upload a single file with json data(only working with json string oject) <br>
	 * sample - {"id":9,"name":"Folau","age":32}
	 */
	@ApiOperation(value = "Upload Single File With Json Stringtified Data", notes = "{\"id\":9,\"name\":\"Folau\",\"age\":32}")
	@RequestMapping(value = {
			"/uploadSingleFileWithJsonStringtifiedData" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Attachment> uploadSingleFileWithJsonStringtifiedObject(@RequestParam("user") String jsonUser,
			@RequestParam("file") MultipartFile multipartFile) {
		log.info("uploading attachment to server. File size: {}", multipartFile.getSize());
		log.info("json user: " + User.fromJson(jsonUser).toString());
		return new ResponseEntity<>(attachmentService.saveFile(multipartFile), HttpStatus.OK);
	}

	/**
	 * Using @ModelAttribute to upload a single file with json data(only working
	 * with json string oject) <br>
	 * sample - {"id":9,"name":"Folau","age":32} <br>
	 * Not working<br>
	 * 
	 * @RequestBody not working either
	 */
	@ApiOperation(value = "Upload Single File Within Json Object", notes = "Not working Using @ModelAttribute, @RequestBody not working either")
	@RequestMapping(value = {
			"/uploadSingleFileWithinJsonObject" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Attachment> uploadSingleFileWithinJsonObject(@ModelAttribute User user) {
		log.info("uploadSingleFileWithJsonObject. user: {}", user.toString());
		// log.info("json user: " + user.toString());
		return new ResponseEntity<>(attachmentService.saveFile(user.getFile()), HttpStatus.OK);
	}

	/**
	 * Upload a single file with json data(only working with json string oject) <br>
	 * sample - {"id":9,"name":"Folau","age":32}
	 */
	@RequestMapping(value = {
			"/uploadMultipleFilesWithJsonStringtifiedData" }, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Attachment>> uploadMultipleFilesWithJsonStringtifiedObject(
			@RequestParam("user") String jsonUser, @RequestParam("files") List<MultipartFile> multipartFiles) {
		log.info("uploadMultipleFilesWithJsonStringtifiedObject. File size: {}", multipartFiles.size());
		log.info("json user: " + User.fromJson(jsonUser).toString());
		return new ResponseEntity<>(attachmentService.saveMultipartFiles(multipartFiles), HttpStatus.OK);
	}

//	/**
//	 * Upload a single file with json data not working <br>
//	 * 
//	 */
//	@RequestMapping(value = "/uploadWithDatad", method = RequestMethod.POST, consumes = {
//			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
//
//	public ResponseEntity<Attachment> uploadSingleFileWithJsonObject(@ModelAttribute User user) {
//		log.info("age={}", user.getAge());
//		return new ResponseEntity<>(attachmentService.saveFile(user.getFile()), HttpStatus.OK);
//	}
}
