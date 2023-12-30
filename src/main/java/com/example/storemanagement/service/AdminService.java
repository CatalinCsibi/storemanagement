package com.example.storemanagement.service;

import com.example.storemanagement.dto.UserDto;
import com.example.storemanagement.exception.ProductAlreadyRegisteredException;
import com.example.storemanagement.exception.ProductNotRegisteredException;
import com.example.storemanagement.exception.UserAlreadyRegisteredException;
import com.example.storemanagement.exception.UserNotRegisteredException;
import com.example.storemanagement.mapper.UserMapper;
import com.example.storemanagement.model.Product;
import com.example.storemanagement.model.user.Role;
import com.example.storemanagement.model.user.User;
import com.example.storemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto addAdmin(User user) {
        log.info("Adding new admin with name: ");

        userRepository.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    throw new UserAlreadyRegisteredException("User with email %s is already registered", user.getEmail());
                });

        var admin = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(admin);

        return userMapper.userToUserDto(admin);
    }

    public List<UserDto> findAllUsers() {
        log.info("Finding all users.");
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(String email) {
        log.info("Deleting user with email: {}", email);
        userRepository.findByEmail(email).ifPresentOrElse(
                userRepository::delete,
                () ->
                {
                    throw new UserNotRegisteredException("User with email %s, is not registered in our database", email);
                }
        );
    }
}
