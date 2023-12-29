package com.example.storemanagement.mapper;

import com.example.storemanagement.dto.UserDto;
import com.example.storemanagement.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
}
