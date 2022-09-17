package com.underfit.trpo.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "student", indexes = {
        @Index(name = "student_email_key", columnList = "email", unique = true)
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid", nullable = false)
    private Long id;

    @Column(name = "fio", nullable = false, length = 50)
    private String fio;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "gender", length = 2)
    private String gender;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    public Student(String fio, LocalDate birthday, String gender, String phone, String email) {
        this.fio = fio;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }
}