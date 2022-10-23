package com.underfit.trpo.controller;

import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.service.ExamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamServiceImpl examService;

    @GetMapping
    public List<ExamDto> getExams() {
        return examService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ExamDto getExam(@PathVariable Long id) {
        return examService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ExamDto create(@RequestBody ExamDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return examService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ExamDto update(@PathVariable Long id, @RequestBody ExamDto dto) {
        dto.setId(id);
        return examService.save(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        examService.delete(id);
    }
}
