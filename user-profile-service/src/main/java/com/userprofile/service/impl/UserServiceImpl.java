package com.userprofile.service.impl;

import com.userprofile.model.dto.UserDto;
import com.userprofile.repository.UserRepository;
import com.userprofile.repository.entity.UserDetails;
import com.userprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        // Check if user already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("User with email " + userDto.getEmail() + " already exists");
        }
        
        // Convert DTO to Entity
        UserDetails userDetails = convertDtoToEntity(userDto);
        
        // Save user
        UserDetails savedUser = userRepository.save(userDetails);
        
        // Convert Entity back to DTO and return
        return convertEntityToDto(savedUser);
    }
    
    @Override
    public UserDto getUser(Long userId) {
        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        return convertEntityToDto(userDetails);
    }
    
    private UserDetails convertDtoToEntity(UserDto userDto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userDto.getId());
        userDetails.setFirstName(userDto.getFirstName());
        userDetails.setLastName(userDto.getLastName());
        userDetails.setEmail(userDto.getEmail());
        userDetails.setPhoneNumber(userDto.getPhoneNumber());
        userDetails.setAddress(userDto.getAddress());
        return userDetails;
    }
    
    private UserDto convertEntityToDto(UserDetails userDetails) {
        UserDto userDto = new UserDto();
        userDto.setId(userDetails.getId());
        userDto.setFirstName(userDetails.getFirstName());
        userDto.setLastName(userDetails.getLastName());
        userDto.setEmail(userDetails.getEmail());
        userDto.setPhoneNumber(userDetails.getPhoneNumber());
        userDto.setAddress(userDetails.getAddress());
        return userDto;
    }
}
