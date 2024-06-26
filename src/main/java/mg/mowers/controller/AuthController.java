package mg.mowers.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.mowers.entity.User;
import mg.mowers.security.JWTUtil;
import mg.mowers.security.JwtUserDetails;
import mg.mowers.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, JWTUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        System.out.println("Name: " + authRequest.getName());
        System.out.println("Password: " + authRequest.getPassword());
        System.out.println("Email: " + authRequest.getEmail());
        System.out.println("Role: " + authRequest.getRole());
        User user = new User();
        user.setAvatarPath(authRequest.getAvatarPath());
        user.setName(authRequest.getName());
        user.setSurname(authRequest.getSurname());
        user.setPasswordHash(passwordEncoder.encode(authRequest.getPassword()));
        user.setEmail(authRequest.getEmail());
        user.setRole(authRequest.getRole());
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);

        // Возвращаем токен и имя пользователя
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("username", userDetails.getUsername());

        return ResponseEntity.ok(response);
    }
}
