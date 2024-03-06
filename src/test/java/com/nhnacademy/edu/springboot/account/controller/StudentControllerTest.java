package com.nhnacademy.edu.springboot.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.account.domain.Student;
import com.nhnacademy.edu.springboot.account.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.account.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@Slf4j
@WebMvcTest(StudentController.class)
@MockBean(JpaMetamodelMappingContext.class)
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper;
    @MockBean
    StudentRepository studentRepository;
    Student testStudent;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        testStudent = new Student(7L, "student", "test@test.kr", 99, "test");
    }

    @Test
    void viewStudent_success() throws Exception {

        when(studentRepository.existsById(anyLong())).thenReturn(true);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(testStudent));

        mockMvc.perform(get("/student/1"))
                .andExpect(model().attribute("student", testStudent));
    }

    @Test
    void viewStudent_error() throws Exception {

        when(studentRepository.existsById(anyLong())).thenReturn(false);

        mockMvc.perform(get("/student/1"))
                .andExpect(result -> Assertions.assertInstanceOf(StudentNotFoundException.class, result.getResolvedException())); //응답 본문의 내용을 검증
    }

    @Test
    void viewStudentWithScoreHidden() throws Exception {
        when(studentRepository.existsById(anyLong())).thenReturn(true);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(testStudent));

        mockMvc.perform(get("/student/1?hideScore=yes"))
                .andExpect(model().attribute("student",
                        hasProperty("comment",
                                equalTo("*****"))
                ));
    }

    @Test
    void viewStudentWithScoreHidden_error() throws Exception {

        when(studentRepository.existsById(anyLong())).thenReturn(false);

        mockMvc.perform(get("/student/1?hideScore=yes"))
                .andExpect(result -> Assertions.assertInstanceOf(StudentNotFoundException.class, result.getResolvedException())); //응답 본문의 내용을 검증
    }
}