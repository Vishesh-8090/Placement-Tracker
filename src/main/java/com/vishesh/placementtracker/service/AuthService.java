package com.vishesh.placementtracker.service;

import com.vishesh.placementtracker.dto.request.LoginRequest;
import com.vishesh.placementtracker.dto.request.RegisterRequest;
import com.vishesh.placementtracker.dto.response.LoginResponse;
import com.vishesh.placementtracker.dto.response.RegisterResponse;
import com.vishesh.placementtracker.entity.User;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

    User getAuthenticatedUser();
}
