package com.nuritech.excs.restapi.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String email);

    @Query("SELECT p FROM User p ORDER BY p.id DESC")
    List<User> findAllDesc();

}

