package com.userprofile.service;

import com.userprofile.model.request.UserRequest;
import com.userprofile.model.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUser(Long userId);
}
