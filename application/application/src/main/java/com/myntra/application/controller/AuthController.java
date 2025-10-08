package com.myntra.application.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myntra.application.dto.AuthRequest;
import com.myntra.application.dto.AuthResponse;
import com.myntra.application.dto.RegisterRequest;
import com.myntra.application.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
		authService.register(req);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
		AuthResponse resp = authService.login(req);
		return ResponseEntity.ok(resp);
	}

	// Simple test endpoint for CORS
	@GetMapping("/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("CORS is working!");
	}

// simple protected endpoint for testing JWT
	@GetMapping("/me")
	public ResponseEntity<?> me() {
		return ResponseEntity.ok("You are authenticated");
	}
}