package com.underfit.trpo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherid", nullable = false)
    private Long id;

    @Column(name = "fio", nullable = false, length = 50)
    private String fio;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "gender", length = 2)
    private String gender;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "teacherdegree", length = 50)
    private String teacherdegree;

    @Column(name = "phone", length = 15)
    private String phone;

    public Teacher(String fio, LocalDate birthday, String gender, String title, String teacherdegree, String phone) {
        this.fio = fio;
        this.birthday = birthday;
        this.gender = gender;
        this.title = title;
        this.teacherdegree = teacherdegree;
        this.phone = phone;
    }
}