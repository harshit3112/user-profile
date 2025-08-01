package com.userprofile.service.impl;

import com.userprofile.model.request.UserRequest;
import com.userprofile.model.response.UserResponse;
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
    public UserResponse createUser(UserRequest userRequest) {
        // Check if user already exists
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User with email " + userRequest.getEmail() + " already exists");
        }
        
        // Convert DTO to Entity
        UserDetails userDetails = convertDtoToEntity(userRequest);
        
        // Save user
        UserDetails savedUser = userRepository.save(userDetails);
        
        // Convert Entity back to DTO and return
        return convertEntityToDto(savedUser);
    }
    
    @Override
    public UserResponse getUser(Long userId) {
        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        return convertEntityToDto(userDetails);
    }
    
    private UserDetails convertDtoToEntity(UserRequest userRequest) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userRequest.getFirstName());
        userDetails.setLastName(userRequest.getLastName());
        userDetails.setEmail(userRequest.getEmail());
        userDetails.setPhoneNumber(userRequest.getPhoneNumber());
        userDetails.setAddress(userRequest.getAddress());
        return userDetails;
    }
    
    private UserResponse convertEntityToDto(UserDetails userDetails) {
        UserResponse response = new UserResponse();
        response.setUserId(userDetails.getId());
        response.setFirstName(userDetails.getFirstName());
        response.setLastName(userDetails.getLastName());
        response.setEmail(userDetails.getEmail());
        response.setPhoneNumber(userDetails.getPhoneNumber());
        response.setAddress(userDetails.getAddress());
        return response;
    }
}
