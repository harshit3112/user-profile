package com.userprofile.controller;

import com.userprofile.model.dto.UserDto;
import com.userprofile.model.response.ApiResponse;
import com.userprofile.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@Tag(name = "User Management", description = "APIs for managing user profiles")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user profile with the provided information")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            UserDto createdUser = userService.createUser(userDto);
            ApiResponse<UserDto> response = ApiResponse.success(createdUser, "User created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<UserDto> response = ApiResponse.error(e.getMessage(), "USER_CREATION_ERROR");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieves user profile information by user ID")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        try {
            UserDto user = userService.getUser(userId);
            ApiResponse<UserDto> response = ApiResponse.success(user, "User retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<UserDto> response = ApiResponse.error(e.getMessage(), "USER_NOT_FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
