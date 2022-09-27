package com.underfit.trpo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectid", nullable = false)
    private Long id;

    @Column(name = "subjectname", nullable = false, length = 50)
    private String subjectname;

    public Subject(String subjectname) {
        this.subjectname = subjectname;
    }
}