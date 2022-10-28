package com.underfit.trpo.servicetest;

import com.underfit.trpo.dao.StudentRepository;
import com.underfit.trpo.dto.StudentDto;
import com.underfit.trpo.entities.Student;
import com.underfit.trpo.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceImplTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    public void setup() {
        student = new Student(1L,"Андрей", LocalDate.of(1999, 3, 20), "м", "4354543", "ffgfg@mail.ru");
    }

    @Test
    public void getByIdTest() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        StudentDto studentDto = studentService.getById(student.getId());
        Assertions.assertEquals(student.getId(), studentDto.toEntity().getId());
    }

    @Test
    public void getAllWhenNullTest() {
        when(studentRepository.findAll()).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> studentService.getAll());
    }

    @Test
    public void getAllTest() {
        Student student1 = new Student(1L,"Юра", LocalDate.of(1999, 6, 21), "м", "4354543", "ffgfg@mail.ru");
        List<Student> list = List.of(student, student1);
        when(studentRepository.findAll()).thenReturn(list);
        List<StudentDto> studentsDto = studentService.getAll();
        assertThat(studentsDto).hasSize(2);
    }

    @Test
    public void saveTest() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        StudentDto studentDto = StudentDto.toDto(student);
        StudentDto saved = studentService.save(studentDto);
        Assertions.assertEquals(student.getId(), saved.getId());
    }

    @Test
    public void deleteTest() {
        doNothing().when(studentRepository).deleteById(1L);
        studentService.delete(student.getId());
        Assertions.assertFalse(studentRepository.findById(student.getId()).isPresent());
    }
}
