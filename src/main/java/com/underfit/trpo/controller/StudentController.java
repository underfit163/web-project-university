package com.underfit.trpo.controller;


import com.underfit.trpo.dto.StudentDto;
import com.underfit.trpo.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDto create(@RequestBody StudentDto studentDto) {
        if (studentDto.getId() != null) {
            studentDto.setId(null);
        }
        return studentService.save(studentDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        studentDto.setId(id);
        return studentService.save(studentDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
