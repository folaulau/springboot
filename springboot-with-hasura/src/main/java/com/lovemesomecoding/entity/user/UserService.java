package com.lovemesomecoding.entity.user;

import javax.validation.Valid;

import com.lovemesomecoding.dto.AuthenticationResponseDTO;
import com.lovemesomecoding.dto.SignUpDTO;

public interface UserService {

	AuthenticationResponseDTO signUp(@Valid SignUpDTO signUpDTO);

}
