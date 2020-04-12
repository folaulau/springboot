package com.folaukaveinga.multidbs.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "member", produces = "Rest API for admin member operations", tags = "Admin Member API")
@RestController
@RequestMapping("/admin")
public class AdminMemberController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminMemberService adminMemberService;

	@ApiOperation(value = "Save Member")
	@PostMapping(value = "/members")
	public ResponseEntity<Member> save(@RequestBody Member member) {
		log.info("save({})", member.toString());
		return new ResponseEntity<>(adminMemberService.save(member), HttpStatus.OK);
	}

	@ApiOperation(value = "Get Member By Id")
	@GetMapping(value = "/members/{id}")
	public ResponseEntity<Member> getById(@PathVariable long id) {
		log.info("getById({})", id);
		return new ResponseEntity<>(adminMemberService.getById(id), HttpStatus.OK);
	}
}
