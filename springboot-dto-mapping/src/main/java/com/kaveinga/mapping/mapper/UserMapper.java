package com.kaveinga.mapping.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kaveinga.mapping.dto.UserCreateDTO;
import com.kaveinga.mapping.dto.UserMobDTO;
import com.kaveinga.mapping.dto.UserWebDTO;
import com.kaveinga.mapping.model.User;

@Mapper(componentModel="spring")
public interface UserMapper {
	
	@Mapping(source="dob", target = "dateOfBirth")
	User userCreateDtoToUser(UserCreateDTO userCreateDto);
	
	@Mapping(source="dateOfBirth", target = "dob")
	UserWebDTO userToUserWebDTO(User user);
	
	@Mapping(source="dateOfBirth", target = "dob")
	UserMobDTO userToUserMobDTO(User user);
}
