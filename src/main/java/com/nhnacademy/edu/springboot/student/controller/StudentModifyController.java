package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.Student;
import com.nhnacademy.edu.springboot.student.domain.StudentModifyRequest;
import com.nhnacademy.edu.springboot.student.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.student.exception.ValidationFailedException;
import com.nhnacademy.edu.springboot.student.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

    public StudentModifyController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentModifyForm(@PathVariable Long studentId, Model model) {

        if (studentId != null && studentRepository.existsById(studentId)) {
            model.addAttribute("student", studentRepository.findById(studentId).get());
            return "studentModify";

        } else throw new StudentNotFoundException();
    }

    @PostMapping
    public String modifyStudent(@PathVariable Long studentId,
                                @Valid @ModelAttribute StudentModifyRequest studentModifyRequest,
                                BindingResult bindingResult,
                                Model model) {

        if (studentId != null && studentRepository.existsById(studentId)) {

            if (bindingResult.hasErrors()) {
                throw new ValidationFailedException(bindingResult);
            }

            Student student = Student.construct(studentId, studentModifyRequest.getName(), studentModifyRequest.getEmail(),
                    studentModifyRequest.getScore(), studentModifyRequest.getComment());

            studentRepository.save(student);

            model.addAttribute("student", student);
            return "modifySuccess";

        } else throw new StudentNotFoundException();
    }

}
