package com.vishesh.placementtracker.service;

import com.vishesh.placementtracker.dto.request.RegisterRequest;
import com.vishesh.placementtracker.dto.response.ApiResponse;

public interface AuthService {

    ApiResponse register(RegisterRequest request);
}
