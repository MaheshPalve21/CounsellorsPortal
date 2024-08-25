package com.course.App.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.course.App.Entity.Student;
import com.course.App.Entity.User;

public interface StudentRepository extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student> {
    List<Student> findByUser(User user);
    long countByCourseStatus(String courseStatus);
}


