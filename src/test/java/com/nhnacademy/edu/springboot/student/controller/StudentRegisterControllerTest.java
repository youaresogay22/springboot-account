package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.Student;
import com.nhnacademy.edu.springboot.student.domain.StudentRegisterRequest;
import com.nhnacademy.edu.springboot.student.exception.ValidationFailedException;
import com.nhnacademy.edu.springboot.student.repository.StudentRepository;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
@WebMvcTest(StudentRegisterController.class)
@MockBean(JpaMetamodelMappingContext.class)
class StudentRegisterControllerTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;
    @MockBean
    StudentRepository studentRepository;
    Student testStudent;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        testStudent = new Student(1992L, "student3", "test3@test.kr", 42, "test3");
    }

    @Test
    void studentRegisterForm() throws Exception {
        mockMvc.perform(get("/student/register"))
                .andExpect(view().name("studentRegister"));
    }

    @Test
    void registerStudent() throws Exception {

        StudentRegisterRequest testRequest =
                new StudentRegisterRequest("student3", "test3@test.kr", 42, "test3");

        when(studentRepository.save(any())).thenReturn(testStudent);

        mockMvc.perform(post("/student/register").flashAttr("studentRegisterRequest", testRequest))
                .andExpect(view().name("registerSuccess"))
                .andExpect(model().attribute("student",
                        hasProperty("name",
                                equalTo("student3"))
                ));
    }

    @Test
    void registerStudent_bindError() throws Exception {
        StudentRegisterRequest testRequest = new StudentRegisterRequest("student", "error email", 999, "test");

        mockMvc.perform(post("/student/register").flashAttr("studentRequest", testRequest))
                .andExpect(result -> Assertions.assertInstanceOf(ValidationFailedException.class, result.getResolvedException()));
    }

}