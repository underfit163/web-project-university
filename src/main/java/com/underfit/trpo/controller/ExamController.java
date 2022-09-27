package com.underfit.trpo.controller;

import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.service.ExamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
    public ExamDto create(@RequestBody ExamDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return examService.save(dto);
    }

    @PutMapping(path = "/{id}")
    public ExamDto update(@PathVariable Long id, @RequestBody ExamDto dto) {
        dto.setId(id);
        return examService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        examService.delete(id);
    }
}
