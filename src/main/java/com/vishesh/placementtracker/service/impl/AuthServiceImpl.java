package com.vishesh.placementtracker.service.impl;

import com.vishesh.placementtracker.dto.request.RegisterRequest;
import com.vishesh.placementtracker.dto.response.ApiResponse;
import com.vishesh.placementtracker.entity.User;
import com.vishesh.placementtracker.exception.EmailAlreadyExistsException;
import com.vishesh.placementtracker.mapper.UserMapper;
import com.vishesh.placementtracker.repository.UserRepository;
import com.vishesh.placementtracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public ApiResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .message("User registered successfully.")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
