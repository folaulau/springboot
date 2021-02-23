package com.lovemesomecoding.entity.user;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lovemesomecoding.dto.AuthenticationResponseDTO;
import com.lovemesomecoding.dto.EntityDTOMapper;
import com.lovemesomecoding.dto.HasuraRequestDTO;
import com.lovemesomecoding.dto.SignUpDTO;
import com.lovemesomecoding.utils.ObjMapperUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(value = "users", tags = "Users")
@Slf4j
@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private EntityDTOMapper entityDTOMapper;

	/**
	 * sign up
	 */
	@ApiOperation(value = "Sign Up")
	@PostMapping(value = "/users/signup")
	public ResponseEntity<AuthenticationResponseDTO> signUp(@RequestBody HasuraRequestDTO body) {
		log.info("sign up {}", ObjMapperUtils.toJson(body));

		SignUpDTO signUpDTO = null;
		AuthenticationResponseDTO userAuthenticationSuccessDTO = userService.signUp(signUpDTO);

		log.info("auth={}", ObjMapperUtils.toJson(userAuthenticationSuccessDTO));
		
		if(true) {
			throw new IllegalArgumentException("something went wrong");
		}

		return new ResponseEntity<>(userAuthenticationSuccessDTO, OK);
	}

	/**
	 * log in
	 */
	@ApiOperation(value = "Log In")
	@PostMapping(value = "/users/login")
	public ResponseEntity<AuthenticationResponseDTO> login(
			@RequestParam(name = "type", required = true) String loginType,
			@RequestHeader(name = "Authorization", required = true) String authorization) {
		log.info("login authorization={}", ObjMapperUtils.toJson(authorization));
		return new ResponseEntity<>(OK);
	}

}
