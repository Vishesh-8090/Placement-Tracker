package com.vishesh.placementtracker.mapper;

import com.vishesh.placementtracker.dto.request.RegisterRequest;
import com.vishesh.placementtracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return user;
    }
}
