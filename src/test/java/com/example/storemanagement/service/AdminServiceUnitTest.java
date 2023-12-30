package com.example.storemanagement.service;

import com.example.storemanagement.dto.UserDto;
import com.example.storemanagement.exception.ProductNotRegisteredException;
import com.example.storemanagement.exception.UserAlreadyRegisteredException;
import com.example.storemanagement.exception.UserNotRegisteredException;
import com.example.storemanagement.mapper.UserMapper;
import com.example.storemanagement.mapper.UserMapperImpl;
import com.example.storemanagement.model.user.Role;
import com.example.storemanagement.model.user.User;
import com.example.storemanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceUnitTest {


    @InjectMocks
    private AdminService adminService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = new UserMapperImpl();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(adminService, "userMapper", userMapper);
    }

    @Test
    public void testAddAdminWithSuccess() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        var admin = adminService.addAdmin(getAdmin());

        assertEquals(getUserDto(), admin);
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testAddAdminFailureWhenUserIsAlreadyRegistered() {
        var admin = getAdmin();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(admin));

        assertThrows(UserAlreadyRegisteredException.class, () -> {
            assert admin != null;
            adminService.addAdmin(admin);
        });

        verify(userRepository, times(1)).findByEmail(any());
    }

    @Test
    public void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(getListOfUsers());

        List<UserDto> actualResult = adminService.findAllUsers();

        assertEquals(getListOfUserDto(), actualResult);
    }

    @Test
    public void testDeleteUserWithSuccess() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(getUser()));

        adminService.deleteUser("second@mail.com");

        verify(userRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteUserFailureWhenUserWasNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotRegisteredException.class, () -> {
            adminService.deleteUser(anyString());
        });

        verify(userRepository, times(1)).findByEmail(any());
        verify(userRepository, times(0)).delete(any());
    }

    private User getAdmin() {
        return User.builder()
                .id(1)
                .firstName("first")
                .lastName("last")
                .email("first@mail.com")
                .password("password")
                .role(Role.ADMIN)
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(2)
                .firstName("second")
                .lastName("last2")
                .email("second@mail.com")
                .password("password2")
                .role(Role.USER)
                .build();
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .firstName("first")
                .lastName("last")
                .email("first@mail.com")
                .build();
    }

    private UserDto getSecondUserDto() {
        return UserDto.builder()
                .firstName("second")
                .lastName("last2")
                .email("second@mail.com")
                .build();
    }

    private List<User> getListOfUsers() {
        return List.of(getAdmin(), getUser());
    }

    private List<UserDto> getListOfUserDto() {
        return List.of(getUserDto(), getSecondUserDto());
    }
}
