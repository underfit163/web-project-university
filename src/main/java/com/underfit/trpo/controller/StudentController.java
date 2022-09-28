package com.underfit.trpo.controller;


import com.underfit.trpo.dto.StudentDto;
import com.underfit.trpo.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping
    public List<StudentDto> getMarks() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentDto getMark(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public StudentDto create(@RequestBody StudentDto studentDto) {
        if (studentDto.getId() != null) {
            studentDto.setId(null);
        }
        return studentService.save(studentDto);
    }

    @PutMapping("/{id}")
    public StudentDto update(@RequestParam Long id, @RequestBody StudentDto studentDto) {
        studentDto.setId(id);
        return studentService.save(studentDto);
    }

    @DeleteMapping()
    public void delete(@RequestParam Long id) {
        studentService.delete(id);
    }
}
