package com.myntra.application.repository;

import com.myntra.application.model.Address;
import com.myntra.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    // Find all addresses for a user
    List<Address> findByUserOrderByIsDefaultDesc(User user);
    
    // Find all addresses for a user by user ID
    List<Address> findByUserIdOrderByIsDefaultDesc(Long userId);
    
    // Find default address for a user
    Address findByUserAndIsDefaultTrue(User user);
    
    // Find default address for a user by user ID
    Address findByUserIdAndIsDefaultTrue(Long userId);
}
