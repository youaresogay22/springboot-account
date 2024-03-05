package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.domain.Student;
import com.nhnacademy.edu.springboot.account.domain.StudentRegisterRequest;
import com.nhnacademy.edu.springboot.account.exception.ValidationFailedException;
import com.nhnacademy.edu.springboot.account.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@Valid @ModelAttribute StudentRegisterRequest studentRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.save(Student.constructIdGeneratedStudent(studentRequest.getName(), studentRequest.getEmail(),
                studentRequest.getScore(), studentRequest.getComment()));

        ModelAndView mav = new ModelAndView("registerSuccess");
        mav.addObject("student", student);
        return mav;
    }

}
