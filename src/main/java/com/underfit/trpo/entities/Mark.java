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
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "markid", nullable = false)
    private Long id;

    @Column(name = "evaluation", length = 10)
    private String evaluation;

    @Column(name = "passdate", nullable = false)
    private LocalDate passdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentidfk")
    @ToString.Exclude
    private Student studentidfk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examidfk")
    @ToString.Exclude
    private Exam examidfk;

    public Mark(String evaluation, LocalDate passdate, Student studentidfk, Exam examidfk) {
        this.evaluation = evaluation;
        this.passdate = passdate;
        this.studentidfk = studentidfk;
        this.examidfk = examidfk;
    }
}