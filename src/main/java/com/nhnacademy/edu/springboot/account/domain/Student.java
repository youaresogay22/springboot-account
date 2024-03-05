package com.nhnacademy.edu.springboot.account.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    private long id;
    private String name;
    private String email;
    private int score;
    private String comment;

    static AtomicLong ID_GENERATOR = new AtomicLong();

    public static Student construct(long id, String name, String email, int score, String comment) {
        return new Student(id, name, email, score, comment);
    }

    public static Student constructIdGeneratedStudent(String name, String email, int score, String comment) {
        return Student.construct(ID_GENERATOR.incrementAndGet(), name, email, score, comment);
    }

    private static final int MASK_SCORE = -1;
    private static final String MASK_COMMENT = "*****";

    public static Student constructScoreAndCommentMaskedStudent(Student student) {
        return new Student(student.getId(), student.getName(), student.getEmail(), MASK_SCORE, MASK_COMMENT);
    }

}
