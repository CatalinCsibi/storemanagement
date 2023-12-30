package com.example.storemanagement.controller;

import com.example.storemanagement.dto.UserDto;
import com.example.storemanagement.model.user.User;
import com.example.storemanagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/add")
    public ResponseEntity<UserDto> addAdmin(@RequestBody User user) {
        var admin = adminService.addAdmin(user);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return new ResponseEntity<>(adminService.findAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("email") String email) {
        adminService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
