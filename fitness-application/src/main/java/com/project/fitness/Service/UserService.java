package com.project.fitness.Service;
import com.project.fitness.Model.User;
import com.project.fitness.Model.UserRole;
import com.project.fitness.Repository.UserRepository;
import com.project.fitness.dto.LoginRequest;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest registerRequest) {
        UserRole role = registerRequest.getRole()!= null ?
                registerRequest.getRole(): UserRole.USER;
        User user = User.builder().
                firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();

        User Saveduser  = userRepository.save(user);
        return mapToResponse(Saveduser);
    }

    public UserResponse mapToResponse(User saveduser) {
      UserResponse response = new UserResponse();
      response.setId(saveduser.getId());
      response.setEmail(saveduser.getEmail());
      response.setFirstName(saveduser.getFirstName());
      response.setLastName(saveduser.getLastName());
      response.setCreatedAt(saveduser.getCreatedAt());
      response.setUpdatedAt(saveduser.getUpdatedAt());
      response.setRole(saveduser.getRole());
      return  response;
    }

    public User authenticateUser(LoginRequest loginRequest) {
            User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
                throw new RuntimeException(loginRequest.getEmail());
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new RuntimeException(loginRequest.getEmail());
            }
            return user;

        }
}
