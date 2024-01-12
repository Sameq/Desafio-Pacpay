package com.picpay.picpay.repository;

import com.picpay.picpay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocument(String document);
}
