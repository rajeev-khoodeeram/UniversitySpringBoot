package ca.cloudace.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.dto.AuthRequest;
import ca.cloudace.backend.dto.AuthResponse;
import ca.cloudace.backend.dto.RegisterRequest;
import ca.cloudace.backend.model.Login;
import ca.cloudace.backend.repository.LoginRepository;
import ca.cloudace.backend.security.JwtUtil;
import ca.cloudace.backend.security.TokenBlackListService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // For development only; restrict in production
public class AuthController {
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager, LoginRepository userRepository,
            PasswordEncoder passwordEncoder, JwtUtil jwtUtil, TokenBlackListService tokenBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration authConfig) throws
    // Exception {
    // // This retrieves the AuthenticationManager built by Spring
    // return authConfig.getAuthenticationManager();
    // }

    private final LoginRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final TokenBlackListService tokenBlacklistService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByUsername(req.username()))
            return ResponseEntity.badRequest().body("username taken");
        // if (userRepository.existsByEmail(req.email()))
        // return ResponseEntity.badRequest().body("email taken");
        Login u = new Login();
        u.setUsername(req.username());
        u.setPasswordHash(passwordEncoder.encode(req.password()));
        u.setRole("student");
        u.setUserId(8);
        u.setUserType("student");
        userRepository.save(u);
        return ResponseEntity.ok(Map.of("message", "registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        System.out.println("Im inside login");

        String username = req.username();
        String password = req.password();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtUtil.generateToken((UserDetails) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));

        // return ResponseEntity.ok(new AuthResponse("dummy-jwt-token", "Bearer"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return ResponseEntity.badRequest().body("No token");
        String token = authHeader.substring(7);
        tokenBlacklistService.blacklist(token);
        return ResponseEntity.ok(Map.of("message", "logged out"));
    }
}