package com.vishesh.placementtracker.service;

import com.vishesh.placementtracker.dto.request.LoginRequest;
import com.vishesh.placementtracker.dto.request.RegisterRequest;
import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.dto.response.LoginResponse;

public interface AuthService {

    ApiResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
