package com.lovemesomecoding.entity.user;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lovemesomecoding.dto.EntityDTOMapper;
import com.lovemesomecoding.dto.UserDTO;
import com.lovemesomecoding.dto.UserUpdateDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO         userDAO;

    @Autowired
    private EntityDTOMapper entityMapper;

    @Override
    public UserDTO getByUuid(String uuid) {
        // TODO Auto-generated method stub
        User user = userDAO.getByUuid(uuid);

        UserDTO userDTO = entityMapper.mapUserToUserDTO(user);

        return userDTO;
    }

    @Override
    public UserDTO updateProfile(UserUpdateDTO userUpdateDTO) {
        // TODO Auto-generated method stub
        User user = userDAO.getByUuid(userUpdateDTO.getUuid());

        user = userDAO.save(user);

        UserDTO userDTO = entityMapper.mapUserToUserDTO(user);

        return userDTO;
    }

}
