package com.lovemesomecoding.entity.user;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.lovemesomecoding.dto.UserDTO;
import com.lovemesomecoding.dto.UserUpdateDTO;

public interface UserService {

    UserDTO getByUuid(String uuid);

    UserDTO updateProfile(UserUpdateDTO userUpdateDTO);

}
