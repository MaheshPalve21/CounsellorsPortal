package com.course.App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.App.Entity.Student;
import com.course.App.Entity.User;
import com.course.App.repo.StudentRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findByUser(User user) {
        return studentRepository.findByUser(user);
    }
    
    

        public long getTotalStudentsCount() {
            return studentRepository.count();
        }

        public long getNewStudentsCount() {
            return studentRepository.countByCourseStatus("New");
        }

        public long getEnrolledStudentsCount() {
            return studentRepository.countByCourseStatus("Enrolled");
        }

        public long getLostStudentsCount() {
            return studentRepository.countByCourseStatus("Lost");
        }
        
        public List<Student> getAllStudents() {
            return studentRepository.findAll();
        }
        
            public List<Student> getFilteredStudents( String course, String mode, String status) {
                return studentRepository.findAll((root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();

            
                    if (course != null && !course.isEmpty()) {
                        predicates.add(criteriaBuilder.equal(root.get("course"), course));
                    }
                    if (mode != null && !mode.isEmpty()) {
                        predicates.add(criteriaBuilder.equal(root.get("courseMode"), mode));
                    }
                    if (status != null && !status.isEmpty()) {
                        predicates.add(criteriaBuilder.equal(root.get("courseStatus"), status));
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                });
            }

            public Student findById(Long id) {
                return studentRepository.findById(id).orElse(null);
            }

           
        

    }

    

