package com.myntra.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myntra.application.dto.AuthRequest;
import com.myntra.application.dto.AuthResponse;
import com.myntra.application.dto.RegisterRequest;
import com.myntra.application.model.User;
import com.myntra.application.repository.UserRepository;
import com.myntra.application.security.JwtUtil;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	public void register(RegisterRequest req) {
		if (userRepository.existsByEmail(req.getEmail())) {
			throw new RuntimeException("Email already in use");
		}
		User user = new User();
		user.setName(req.getName());
		user.setEmail(req.getEmail());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setPhone(req.getPhone());
		userRepository.save(user);
	}

	public AuthResponse login(AuthRequest req) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
		String token = jwtUtil.generateToken(req.getEmail());
		return new AuthResponse(token);
	}
}
