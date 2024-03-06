package com.nhnacademy.edu.springboot.student.domain;

import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Value
@Valid
@Data
public class StudentRegisterRequest {
    //이름: 공백 제거 후 문자열의 길이가 0보다 커야 함
    @NotBlank
    String name;

    //이메일: 이메일 형식이 맞아야 함
    @Email
    String email;

    //점수; 0점 이상 100점 이하
    @Min(0)
    @Max(100)
    int score;

    //평가: 공백 제거 후 문자열의 길이가 0보다 크고 200보다 같거나 작아야 함
    @NotBlank
    @Length(min = (0), max = (200))
    String comment;
}
