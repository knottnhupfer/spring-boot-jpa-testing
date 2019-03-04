package com.smooth.systems.solutions.boot.jpa.dao;

import com.smooth.systems.solutions.boot.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
