package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.domain.Student;
import com.nhnacademy.edu.springboot.account.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.account.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public ModelAndView viewStudent(@PathVariable("studentId") Long studentId) {

        if (studentId != null && studentRepository.existsById(studentId)) {

            ModelAndView mav = new ModelAndView("studentView");
            mav.addObject("student", studentRepository.findById(studentId).orElse(null));
            return mav;
        } else throw new StudentNotFoundException();

    }

    @GetMapping(value = "/{studentId}", params = "hideScore=yes")
    public String viewStudentWithScoreHidden(@PathVariable Long studentId, Model model) {

        if (studentId != null && studentRepository.existsById(studentId)) {

            Student student = studentRepository.findById(studentId).orElse(null);
            model.addAttribute("student", Student.constructScoreAndCommentMaskedStudent(student));
            return "studentView";
        } else
            throw new StudentNotFoundException();
    }

}
