package com.course.App.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.App.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmailAndPassword(String email, String password);
}
