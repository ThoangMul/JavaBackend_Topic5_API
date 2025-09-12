package com.example.demo.jwt;

import com.example.demo.exception.InvalidCredentialsException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public AuthResponse login(AuthRequest req) {
        var u = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!BCrypt.checkpw(req.password(), u.getPasswordHash()))
            throw new InvalidCredentialsException("Invalid credentials");

        return new AuthResponse(jwtService.generateToken(u));
        }

    @Transactional
    public RegisterResponse register(RegisterRequest req) {
        if (req.username() == null || req.username().isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (req.username().contains(" ")) {
            throw new IllegalArgumentException("Username cannot contain spaces");
        }

        if (userRepository.existsByUsername(req.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(req.username());
        user.setPasswordHash(BCrypt.hashpw(req.password(), BCrypt.gensalt()));

        if (req.role() == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(req.role());
        }

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getUsername(), savedUser.getRole().toString());
    }
}

