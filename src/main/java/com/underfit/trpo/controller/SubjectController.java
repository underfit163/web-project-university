package com.underfit.trpo.controller;

import com.underfit.trpo.dto.SubjectDto;
import com.underfit.trpo.service.SubjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectServiceImpl subjectService;

    @GetMapping
    public List<SubjectDto> getSubjects() {
        return subjectService.getAll();
    }

    @GetMapping("/{id}")
    public SubjectDto getSubject(@PathVariable Long id) {
        return subjectService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public SubjectDto create(@RequestBody SubjectDto subjectDto) {
        if (subjectDto.getId() != null) {
            subjectDto.setId(null);
        }
        return subjectService.save(subjectDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SubjectDto update(@PathVariable Long id, @RequestBody SubjectDto subjectDto) {
        subjectDto.setId(id);
        return subjectService.save(subjectDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        subjectService.delete(id);
    }
}
