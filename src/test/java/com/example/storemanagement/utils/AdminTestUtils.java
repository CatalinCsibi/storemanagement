package com.example.storemanagement.utils;

import com.example.storemanagement.dto.UserDto;
import com.example.storemanagement.model.user.Role;
import com.example.storemanagement.model.user.User;

import java.util.List;

public final class AdminTestUtils {

    public static User getAdmin() {
        return User.builder()
                .id(1)
                .firstName("first")
                .lastName("last")
                .email("first@mail.com")
                .password("password")
                .role(Role.ADMIN)
                .build();
    }

    public static User getUser() {
        return User.builder()
                .id(2)
                .firstName("second")
                .lastName("last2")
                .email("second@mail.com")
                .password("password2")
                .role(Role.USER)
                .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                .firstName("first")
                .lastName("last")
                .email("first@mail.com")
                .build();
    }

    public static UserDto getSecondUserDto() {
        return UserDto.builder()
                .firstName("second")
                .lastName("last2")
                .email("second@mail.com")
                .build();
    }

    public static List<User> getListOfUsers() {
        return List.of(getAdmin(), getUser());
    }

    public static List<UserDto> getListOfUserDto() {
        return List.of(getUserDto(), getSecondUserDto());
    }
}
