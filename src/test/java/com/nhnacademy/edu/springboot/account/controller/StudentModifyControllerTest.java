package com.nhnacademy.edu.springboot.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.account.domain.Student;
import com.nhnacademy.edu.springboot.account.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@Slf4j
@WebMvcTest(StudentController.class)
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
    }

    @Test
    void modifyStudent() throws Exception {
    }

    @Test
    void modifyStudent_error() throws Exception {
    }
}