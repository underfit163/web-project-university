package com.underfit.trpo.service;

import com.underfit.trpo.dao.StudentRepository;
import com.underfit.trpo.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements UniversityService<StudentDto> {
    private final StudentRepository studentRepository;
    @Override
    public List<StudentDto> getAll() {
        return studentRepository
                .findAll()
                .stream()
                .map(StudentDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getById(Long id) {
        return StudentDto.toDto(studentRepository.findById(id).orElse(null));
    }

    @Override
    public StudentDto save(StudentDto dto) {
        return StudentDto.toDto(studentRepository.save(dto.toEntity()));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
