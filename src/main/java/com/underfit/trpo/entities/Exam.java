package com.underfit.trpo.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examid", nullable = false)
    private Long id;

    @Column(name = "passtype", nullable = false, length = 50)
    private String passtype;

    @Column(name = "totalhours")
    private Integer totalhours;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectidfk")
    @ToString.Exclude
    private Subject subjectidfk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacheridfk")
    @ToString.Exclude
    private Teacher teacheridfk;

    public Exam(String passtype, Integer totalhours, Integer semester, Subject subjectidfk, Teacher teacheridfk) {
        this.passtype = passtype;
        this.totalhours = totalhours;
        this.semester = semester;
        this.subjectidfk = subjectidfk;
        this.teacheridfk = teacheridfk;
    }
}