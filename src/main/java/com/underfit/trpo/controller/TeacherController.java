package com.underfit.trpo.controller;

import com.underfit.trpo.dto.TeacherDto;
import com.underfit.trpo.service.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    @GetMapping
    public List<TeacherDto> getSubjects() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getSubject(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TeacherDto create(@RequestBody TeacherDto teacherDto) {
        if (teacherDto.getId() != null) {
            teacherDto.setId(null);
        }
        return teacherService.save(teacherDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TeacherDto update(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
        teacherDto.setId(id);
        return teacherService.save(teacherDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}
