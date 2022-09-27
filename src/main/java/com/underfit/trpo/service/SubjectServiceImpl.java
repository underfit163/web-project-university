package com.underfit.trpo.service;


import com.underfit.trpo.dao.SubjectRepository;
import com.underfit.trpo.dto.SubjectDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements UniversityService<SubjectDto> {

    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository
                .findAll()
                .stream()
                .map(SubjectDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getById(Long id) {
        return SubjectDto.toDto(subjectRepository.findById(id).orElse(null));
    }

    @Override
    public SubjectDto save(SubjectDto dto) {
        return SubjectDto.toDto(subjectRepository.save(dto.toEntity()));
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}
