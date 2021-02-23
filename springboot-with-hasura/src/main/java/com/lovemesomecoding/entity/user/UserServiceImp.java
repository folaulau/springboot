package com.lovemesomecoding.entity.user;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.dto.AuthenticationResponseDTO;
import com.lovemesomecoding.dto.SignUpDTO;
import com.lovemesomecoding.jwt.JwtPayload;
import com.lovemesomecoding.jwt.JwtTokenService;
import com.lovemesomecoding.utils.ObjMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private JwtTokenService jwtTokenService;

	@Override
	public AuthenticationResponseDTO signUp(SignUpDTO signUpDTO) {
		log.debug("signup={}", ObjMapperUtils.toJson(signUpDTO));
		AuthenticationResponseDTO response = new AuthenticationResponseDTO();
		JwtPayload payload = new JwtPayload();
		payload.setJti(UUID.randomUUID().toString());
		String accessToken = jwtTokenService.generateToken(payload);
		response.setAccessToken(accessToken);
		return response;
	}

}
