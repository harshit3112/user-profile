package com.userprofile.repository;

import com.userprofile.repository.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    
    @Query("SELECT u FROM UserDetails u WHERE u.email = :email")
    Optional<UserDetails> findByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM UserDetails u WHERE u.id = :id")
    Optional<UserDetails> findById(@Param("id") Long id);
    
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserDetails u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
