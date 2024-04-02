package com.questionpro.grocerybookingapi.service;

import com.questionpro.grocerybookingapi.exception.UserServiceException;
import com.questionpro.grocerybookingapi.repository.UserRepository;
import com.questionpro.grocerybookingapi.security.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static AuthenticationResponse hideUserPassword(User savedUser, String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .id(savedUser.getId())
                .name(savedUser.getName())
                .emailId(savedUser.getEmailId())
                .password("***************") //Hide the password and don't send the real Password
                .isAdmin(Role.ADMIN.equals(savedUser.getRole()))
                .build();
    }

    @Deprecated(forRemoval = true)
    private Optional<User> retrieveUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Deprecated(forRemoval = true)
    private Optional<User> retrieveUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    public AuthenticationResponse addUser(RegisterRequest registerRequest) {
        userRepository.findByEmailId(registerRequest.getEmailId()).ifPresent(x -> {
            throw new UserServiceException("User Registration Error: Email Already Registered");
        });
        var user = User.builder()
                .name(registerRequest.getName())
                .emailId(registerRequest.getEmailId())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Boolean.TRUE.equals(registerRequest.getIsAdmin()) ? Role.ADMIN : Role.USER)
                .build();

        User newUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getEmailId(), user.getRole().toString());
        return hideUserPassword(newUser, jwtToken);
    }

    public AuthenticationResponse loginUser(LoginRequest registerRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmailId(), registerRequest.getPassword()));
        var user = userRepository.findByEmailId(registerRequest.getEmailId()).orElseThrow(() -> new UserServiceException("Login Error: User or Password Invalid"));
        var jwtToken = jwtService.generateToken(user.getEmailId(), user.getRole().toString());
        return hideUserPassword(user, jwtToken);
    }
}