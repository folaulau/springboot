package com.folaukaveinga.api.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.api.user.User;
import com.folaukaveinga.api.user.UserCreateDTO;
import com.folaukaveinga.api.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "files", produces = "Rest API for File Api operations", tags = "File Api Controller")
@RestController
@RequestMapping("/files")
public class FileUploadController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	/**
	 * post json object with a file using @RequestPart
	 */
	@ApiOperation(value = "Post file with RequestPart")
	@PostMapping("/withrequestpart")
	public ResponseEntity<User> postWithRequestPart(@ApiParam(name = "file", required = true, value = "file") @RequestPart(value = "file", required = true) MultipartFile file, @RequestPart("user") User user){
		log.info("postWithRequestPart(..)");
		log.info(user.toJson());
		log.info("file size:{}, name:{}", file.getSize(),file.getName());
		return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
	}
	
	/**
	 * User @ModelAttribute instead of @RequestBody
	 * @param userCreateDTO
	 * @return
	 */
	@ApiOperation(value = "Post file in DTO")
	@PostMapping(value="/withdto")
	public ResponseEntity<User> postWithDTO(@ApiParam(name = "user", required = true, value = "user") @ModelAttribute UserCreateDTO userCreateDTO){
		log.info("postWithDTO(..)");
		log.info("name: {}, email: {}",userCreateDTO.getName(),userCreateDTO.getEmail());
		log.info("file size:{}, name:{}", userCreateDTO.getFile().getSize(),userCreateDTO.getFile().getOriginalFilename());
		
		return new ResponseEntity<>(userService.create(new User(null,null,userCreateDTO.getName(),userCreateDTO.getEmail(),null)), HttpStatus.OK);
	}
	
	

}
