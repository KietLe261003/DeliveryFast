package com.example.UserService.Mapper;

import com.example.UserService.Dto.Request.UserRequest;
import com.example.UserService.Dto.Request.UserUpdateRequest;
import com.example.UserService.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {
    User toUser(UserRequest userCreation);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
