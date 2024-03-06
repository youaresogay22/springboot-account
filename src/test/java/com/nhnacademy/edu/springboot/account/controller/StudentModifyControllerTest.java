package com.nhnacademy.edu.springboot.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.account.domain.Student;
import com.nhnacademy.edu.springboot.account.domain.StudentModifyRequest;
import com.nhnacademy.edu.springboot.account.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.account.exception.ValidationFailedException;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@Slf4j
@WebMvcTest(StudentModifyController.class)
@MockBean(JpaMetamodelMappingContext.class)
class StudentModifyControllerTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;
    ObjectMapper mapper;
    @MockBean
    StudentRepository studentRepository;
    Student testStudent;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        testStudent = new Student(7L, "student", "test@test.kr", 99, "test");
    }

    @Test
    void studentModifyForm() throws Exception {
        when(studentRepository.existsById(anyLong())).thenReturn(true);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(testStudent));

        mockMvc.perform(get("/student/1/modify"))
                .andExpect(model().attribute("student", testStudent));
    }

    @Test
    void studentModifyForm_error() throws Exception {
        when(studentRepository.existsById(anyLong())).thenReturn(false);

        mockMvc.perform(get("/student/1/modify"))
                .andExpect(result -> Assertions.assertInstanceOf(StudentNotFoundException.class, result.getResolvedException())); //응답 본문의 내용을 검증
    }

    @Test
    void modifyStudent() throws Exception {
        StudentModifyRequest testRequest = new StudentModifyRequest("student2", "test2@test.kr", 66, "test2");
        when(studentRepository.existsById(anyLong())).thenReturn(true);

        mockMvc.perform(post("/student/1/modify").flashAttr("studentModifyRequest", testRequest))
                .andExpect(model().attribute("student",
                        hasProperty("name",
                                equalTo("student2"))
                ));
    }

    @Test
    void modifyStudent_bindError() throws Exception {
        StudentModifyRequest testRequest = new StudentModifyRequest("student", "error email", 999, "test");
        when(studentRepository.existsById(anyLong())).thenReturn(true);

        mockMvc.perform(post("/student/1/modify").flashAttr("studentModifyRequest", testRequest))
                .andExpect(result -> Assertions.assertInstanceOf(ValidationFailedException.class, result.getResolvedException()));
    }

    @Test
    void modifyStudent_notFoundError() throws Exception {
        when(studentRepository.existsById(anyLong())).thenReturn(false);

        mockMvc.perform(post("/student/1/modify"))
                .andExpect(result -> Assertions.assertInstanceOf(StudentNotFoundException.class, result.getResolvedException()));

    }
}