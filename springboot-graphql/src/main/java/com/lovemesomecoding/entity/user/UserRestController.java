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

import com.lovemesomecoding.dto.EntityDTOMapper;
import com.lovemesomecoding.dto.UserDTO;
import com.lovemesomecoding.dto.UserUpdateDTO;
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
    private UserService     userService;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @ApiOperation(value = "Get User")
    @GetMapping(value = "/users/{uuid}")
    public ResponseEntity<UserDTO> getByUuid(@RequestHeader(name = "token", required = true) String token, @ApiParam(name = "uuid", value = "user uuid", required = true) @PathVariable String uuid) {
        log.info("getByUuid({})", uuid);

        UserDTO userDTO = userService.getByUuid(uuid);

        log.info("userDTO={}", ObjMapperUtils.toJson(userDTO));

        if (userDTO == null) {
            throw new IllegalAccessError("User not found, User not found by uuid=" + uuid);
        }

        return new ResponseEntity<>(userDTO, OK);
    }

    @ApiOperation(value = "Update Profile")
    @PutMapping(value = "/users/profile")
    public ResponseEntity<UserDTO> updateProfile(@RequestHeader(name = "token", required = true) String token,
            @ApiParam(name = "user", value = "user profile dto", required = true) @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        log.info("updateProfile {}", ObjMapperUtils.toJson(userUpdateDTO));

        UserDTO userDTO = userService.updateProfile(userUpdateDTO);

        return new ResponseEntity<>(userDTO, OK);
    }

}
