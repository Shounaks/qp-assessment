package com.questionpro.grocerybookingapi.controller;

import com.questionpro.grocerybookingapi.security.AuthenticationResponse;
import com.questionpro.grocerybookingapi.security.LoginRequest;
import com.questionpro.grocerybookingapi.security.RegisterRequest;
import com.questionpro.grocerybookingapi.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/grocerybooking/authentication/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.loginUser(loginRequest));
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(loginService.addUser(registerRequest));
    }
}