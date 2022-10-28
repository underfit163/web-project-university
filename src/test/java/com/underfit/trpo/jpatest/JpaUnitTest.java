package com.underfit.trpo.jpatest;

import static org.assertj.core.api.Assertions.assertThat;

import com.underfit.trpo.dao.*;
import com.underfit.trpo.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@DataJpaTest
public class JpaUnitTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private UserRepository userRepository;
    Mark mark4;

    @BeforeEach
    public void setupData() {
        markRepository.deleteAll();
        examRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
        userRepository.deleteAll();

        Subject subject1 = new Subject("noSql");
        Subject subject2 = new Subject("Java");
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        Teacher teacher1 = new Teacher(
                "Анфиса", LocalDate.of(1989, 3, 20), "ж", "доцент",
                "кандидат наук", "4354543");
        Teacher teacher2 = new Teacher(
                "Алексей", LocalDate.of(1978, 3, 20), "м", "доцент",
                "кандидат наук", "111111");
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);

        Exam exam1 = new Exam("экзамен", 54, 1, subject1, teacher1);
        Exam exam2 = new Exam("экзамен", 54, 2, subject2, teacher2);
        examRepository.save(exam1);
        examRepository.save(exam2);

        Student student1 = new Student("Андрей", LocalDate.of(1999, 3, 20), "м", "4354543", "ffgfg@mail.ru");
        Student student2 = new Student("Алексей", LocalDate.of(1998, 12, 31), "м", "784654", "2222@mail.ru");
        studentRepository.save(student1);
        studentRepository.save(student2);

        Mark mark1 = new Mark("5", LocalDate.of(2019, 1, 14), student1, exam1);
        Mark mark2 = new Mark("4", LocalDate.of(2019, 1, 14), student1, exam2);
        Mark mark3 = new Mark("3", LocalDate.of(2019, 1, 14), student2, exam1);
        mark4 = new Mark("3", LocalDate.of(2019, 1, 14), student2, exam2);

        markRepository.save(mark1);
        markRepository.save(mark2);
        markRepository.save(mark3);
        markRepository.save(mark4);

        User user1 = new User("19694283", String.valueOf("123".hashCode()), Role.ROLE_USER, "1332@gmail.com");
        User user2 = new User("1111", String.valueOf("111".hashCode()), Role.ROLE_ADMIN, "1313@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    @Order(1)
    public void findData() {
        userRepository.findAll().forEach(System.out::println);

        assertThat(markRepository.findAllMarks().orElse(null)).hasSize(4);
        assertThat(subjectRepository.findAll()).hasSize(2);
        assertThat(teacherRepository.findAll()).hasSize(2);
        assertThat(examRepository.findAllExams().orElse(null)).hasSize(2);
        assertThat(studentRepository.findAll()).hasSize(2);
        assertThat(userRepository.findAll()).hasSize(2);
    }

    @Test
    @Order(2)
    public void updateData() {
        mark4.setEvaluation("4");
        mark4 = markRepository.save(mark4);
        Assertions.assertEquals("4", mark4.getEvaluation());
    }

    @Test
    @Order(3)
    public void deleteData() {
        Long id = mark4.getId();
        markRepository.deleteById(id);
        Assertions.assertFalse(markRepository.findById(id).isPresent());
    }
}
