package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.nhnacademy.springmvc.repository.StudentRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/student/{studentId}/modify")
@Slf4j
public class StudentModifyController {
    private final StudentRepositoryImpl studentRepository;

    public StudentModifyController(StudentRepository studentRepository) {
        this.studentRepository = (StudentRepositoryImpl) studentRepository;
    }

    @GetMapping
    public String studentModifyForm(@PathVariable Long studentId, Model model) {

        if (studentId != null && studentRepository.exists(studentId)) {
            model.addAttribute("student", studentRepository.getStudent(studentId));
            return "studentModify";

        } else throw new StudentNotFoundException();
    }

    @PostMapping
    public String modifyStudent(@PathVariable Long studentId,
                                @Valid @ModelAttribute StudentModifyRequest studentModifyRequest,
                                BindingResult bindingResult,
                                Model model) {

        if (studentId != null && studentRepository.exists(studentId)) {

            if (bindingResult.hasErrors()) {
                throw new ValidationFailedException(bindingResult);
            }

            Student student = Student.construct(studentId, studentModifyRequest.getName(), studentModifyRequest.getEmail(),
                    studentModifyRequest.getScore(), studentModifyRequest.getComment());

            studentRepository.modify(student);

            model.addAttribute("student", student);
            return "modifySuccess";

        } else throw new StudentNotFoundException();
    }

}
