package ru.matvey.vkscoreboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.matvey.vkscoreboard.model.*;
import ru.matvey.vkscoreboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role._USER)
                .completedTasks(new ArrayList<Task>())
                .category1Score(0)
                .category2Score(0)
                .category3Score(0)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .user(convertUserToResponse(user))
                .build();
    }

    public String getCurrentUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(getCurrentUserEmail()).orElseThrow();
    }

    public List<UserResponse> getAllUsersResponse() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(u -> userResponses.add(convertUserToResponse(u)));
        return userResponses;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserResponse convertUserToResponse(User user) {
        return UserResponse.builder()
                ._id(user.get_id())
                .category1Score(user.getCategory1Score())
                .category2Score(user.getCategory2Score())
                .category3Score(user.getCategory3Score())
                .completedTasks(user.getCompletedTasks())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName()).build();
    }
    public UserResponse getUserResponseByEmail(String email) {
      return convertUserToResponse(userRepository.findByEmail(email).orElseThrow()) ;
    }
    public User getUserById(String userId){
        return userRepository.findById(userId).orElseThrow();
    }
}
