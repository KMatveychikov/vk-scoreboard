package ru.matvey.vkscoreboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matvey.vkscoreboard.model.AuthRequest;
import ru.matvey.vkscoreboard.model.AuthResponse;
import ru.matvey.vkscoreboard.model.RegisterRequest;
import ru.matvey.vkscoreboard.model.UserResponse;
import ru.matvey.vkscoreboard.service.AuthService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/get_all")
    public List<UserResponse> getAllUsers(){
       return service.getAllUsersResponse();
    }

}
