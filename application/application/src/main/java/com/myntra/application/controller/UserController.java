package com.myntra.application.controller;

import com.myntra.application.dto.AddressRequest;
import com.myntra.application.dto.UserProfileRequest;
import com.myntra.application.model.Address;
import com.myntra.application.model.User;
import com.myntra.application.repository.AddressRepository;
import com.myntra.application.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            User user = userOpt.get();
            // Don't return password
            user.setPassword(null);
            
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching user profile: " + e.getMessage());
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UserProfileRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            User user = userOpt.get();
            
            // Check if email is being changed and if it already exists
            if (!user.getEmail().equals(request.getEmail())) {
                Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
                if (existingUser.isPresent()) {
                    return ResponseEntity.badRequest().body("Email already exists");
                }
            }
            
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            
            User updatedUser = userRepository.save(user);
            updatedUser.setPassword(null); // Don't return password
            
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user profile: " + e.getMessage());
        }
    }

    @GetMapping("/addresses")
    public ResponseEntity<?> getUserAddresses() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            List<Address> addresses = addressRepository.findByUserOrderByIsDefaultDesc(userOpt.get());
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching addresses: " + e.getMessage());
        }
    }

    @PostMapping("/addresses")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            User user = userOpt.get();
            
            // If this is set as default, remove default from other addresses
            if (request.getIsDefault()) {
                List<Address> existingAddresses = addressRepository.findByUserOrderByIsDefaultDesc(user);
                for (Address address : existingAddresses) {
                    address.setIsDefault(false);
                    addressRepository.save(address);
                }
            }
            
            Address address = new Address(
                request.getFullName(),
                request.getPhone(),
                request.getAddressLine1(),
                request.getAddressLine2(),
                request.getCity(),
                request.getState(),
                request.getPincode(),
                request.getAddressType(),
                user
            );
            address.setIsDefault(request.getIsDefault());
            
            Address savedAddress = addressRepository.save(address);
            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding address: " + e.getMessage());
        }
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable Long addressId, @Valid @RequestBody AddressRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Optional<Address> addressOpt = addressRepository.findById(addressId);
            if (addressOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Address address = addressOpt.get();
            
            // Check if address belongs to the user
            if (!address.getUser().getEmail().equals(userEmail)) {
                return ResponseEntity.status(403).body("Access denied");
            }
            
            // If this is set as default, remove default from other addresses
            if (request.getIsDefault()) {
                List<Address> existingAddresses = addressRepository.findByUserOrderByIsDefaultDesc(userOpt.get());
                for (Address existingAddress : existingAddresses) {
                    if (!existingAddress.getId().equals(addressId)) {
                        existingAddress.setIsDefault(false);
                        addressRepository.save(existingAddress);
                    }
                }
            }
            
            address.setFullName(request.getFullName());
            address.setPhone(request.getPhone());
            address.setAddressLine1(request.getAddressLine1());
            address.setAddressLine2(request.getAddressLine2());
            address.setCity(request.getCity());
            address.setState(request.getState());
            address.setPincode(request.getPincode());
            address.setAddressType(request.getAddressType());
            address.setIsDefault(request.getIsDefault());
            
            Address updatedAddress = addressRepository.save(address);
            return ResponseEntity.ok(updatedAddress);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating address: " + e.getMessage());
        }
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            
            Optional<User> userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Optional<Address> addressOpt = addressRepository.findById(addressId);
            if (addressOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Address address = addressOpt.get();
            
            // Check if address belongs to the user
            if (!address.getUser().getEmail().equals(userEmail)) {
                return ResponseEntity.status(403).body("Access denied");
            }
            
            addressRepository.delete(address);
            return ResponseEntity.ok("Address deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting address: " + e.getMessage());
        }
    }
}
