package com.folaukaveinga.springboot.rest;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.springboot.jms.Mail;
import com.folaukaveinga.springboot.jms.SenderService;


@RestController
public class HomeRestController {
	@Autowired
	private SenderService senderService;
	
	@RequestMapping(value={"/send-mail/{message}","/send-mail/{message}/"}, method=RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> sendMail(@PathVariable("message")String message){
		Mail mail = new Mail("to","from",message,"subject");
		return new ResponseEntity<>(senderService.sendMail(mail), HttpStatus.OK);
	}
}
