package com.underfit.trpo.service;

import com.underfit.trpo.dao.SubjectRepository;
import com.underfit.trpo.dao.TeacherRepository;
import com.underfit.trpo.dto.SubjectDto;
import com.underfit.trpo.dto.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements UniversityService<TeacherDto> {
    private final TeacherRepository teacherRepository;
    @Override
    public List<TeacherDto> getAll() {
        return teacherRepository
                .findAll()
                .stream()
                .map(TeacherDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto getById(Long id) {
        return TeacherDto.toDto(teacherRepository.findById(id).orElse(null));
    }

    @Override
    public TeacherDto save(TeacherDto dto) {
        return TeacherDto.toDto(teacherRepository.save(dto.toEntity()));
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
