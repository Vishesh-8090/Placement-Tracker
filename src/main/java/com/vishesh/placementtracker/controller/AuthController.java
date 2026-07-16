package com.vishesh.placementtracker.controller;

import com.vishesh.placementtracker.dto.request.LoginRequest;
import com.vishesh.placementtracker.dto.request.RegisterRequest;
import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.dto.response.LoginResponse;
import com.vishesh.placementtracker.dto.response.RegisterResponse;
import com.vishesh.placementtracker.service.AuthService;
import com.vishesh.placementtracker.util.ApiResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest request){

        RegisterResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success(
                        "Registered successfully.",
                        response
                ));
    }

    @Operation(
            summary = "Login",
            description = "Authenticates the user and returns a JWT."
    )
    @PostMapping("login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request){

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Login successful.",
                        response
                )
        );
    }
}
