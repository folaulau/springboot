package com.lovemesomecoding.aws.sqs;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lovemesomecoding.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "queue",produces = "Rest API for queue operations", tags = "Queue Controller")
@RestController
public class QueueController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SQSService sqsService;
	
	/**
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Get All Queue Urls")
	@GetMapping("/queues")
	public ResponseEntity<List<String>> getAllQueueUrls(){
		log.debug("getAllQueueUrls(..)");
		return new ResponseEntity<>(sqsService.getAllQueueUrls(), HttpStatus.OK);
	}
	
}
