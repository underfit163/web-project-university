package com.underfit.trpo.service;

import com.underfit.trpo.dao.ExamRepository;
import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.entities.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements UniversityService<ExamDto> {
    private final ExamRepository examRepository;

    @Override
    public List<ExamDto> getAll() {
        return examRepository.findAll()
                .stream()
                .map(ExamDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto getById(Long id) {
        return ExamDto.toDto(examRepository.findById(id).orElse(null));
    }

    @Override
    public ExamDto save(ExamDto dto) {
        return ExamDto.toDto(examRepository.save(dto.toEntity()));
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}
