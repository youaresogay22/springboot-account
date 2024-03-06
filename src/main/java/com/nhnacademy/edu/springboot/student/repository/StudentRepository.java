package com.nhnacademy.edu.springboot.student.repository;

import com.nhnacademy.edu.springboot.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
