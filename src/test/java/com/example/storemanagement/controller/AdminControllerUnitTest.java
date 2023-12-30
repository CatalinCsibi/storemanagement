package com.example.storemanagement.controller;

import com.example.storemanagement.dto.UserDto;
import com.example.storemanagement.exception.UserAlreadyRegisteredException;
import com.example.storemanagement.exception.UserNotRegisteredException;
import com.example.storemanagement.model.user.User;
import com.example.storemanagement.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.storemanagement.utils.AdminTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerUnitTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Test
    public void addAdminWithSuccess() {
        var admin = getAdmin();

        var userDto = getUserDto();

        when(adminService.addAdmin(admin)).thenReturn(userDto);

        ResponseEntity<UserDto> response = adminController.addAdmin(admin);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(adminService, times(1)).addAdmin(any());
    }

    @Test
    public void addAdminFailureWhenAdminIsAlreadyRegistered() {

        when(adminService.addAdmin(any())).thenThrow(UserAlreadyRegisteredException.class);

        assertThrows(UserAlreadyRegisteredException.class, () -> adminController.addAdmin(any()));
    }

    @Test
    public void testFindAllUsers() {
        List<UserDto> userDtos = getListOfUserDto();

        when(adminService.findAllUsers()).thenReturn(getListOfUserDto());

        ResponseEntity<List<UserDto>> response = adminController.findAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDtos, response.getBody());
        verify(adminService, times(1)).findAllUsers();
    }


    @Test
    public void testDeleteUserWithSuccess() {
        doNothing().when(adminService).deleteUser(any());

        ResponseEntity<Void> response = adminController.deleteUser(any());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(adminService, times(1)).deleteUser(any());
    }

    @Test
    public void testDeleteUserFailureWhenUserCouldNotBeFound() {
        doThrow(new UserNotRegisteredException("", "")).when(adminService).deleteUser(any());

        assertThrows(UserNotRegisteredException.class, () -> adminController.deleteUser(any()));
        verify(adminService, times(1)).deleteUser(any());
    }
}
