package com.userprofile.service;

import com.userprofile.model.dto.UserDto;

public interface UserService {
    
    UserDto createUser(UserDto userDto);
    
    UserDto getUser(Long userId);
}
