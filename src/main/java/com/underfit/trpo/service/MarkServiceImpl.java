package com.underfit.trpo.service;

import com.underfit.trpo.dao.ExamRepository;
import com.underfit.trpo.dao.MarkRepository;
import com.underfit.trpo.dao.StudentRepository;
import com.underfit.trpo.dto.MarkDto;
import com.underfit.trpo.entities.Mark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements UniversityService<MarkDto> {
    private final MarkRepository markRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<MarkDto> getAll() {
        return markRepository
                .findAllMarks().orElseThrow()
                .stream()
                .map(MarkDto::toDto)
                .collect(Collectors.toList());
    }

    public List<MarkDto> getAllByIdExam(Long id) {
        return markRepository
                .findAllMarksByIdExam(id).orElseThrow()
                .stream()
                .map(MarkDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MarkDto getById(Long id) {
        Mark mark = markRepository.findById(id).orElse(null);
        return mark != null ? MarkDto.toDto(mark) : null;
    }

    @Override
    public MarkDto save(MarkDto dto) {
        Mark mark = dto.toEntity();
        if (dto.getExamidfk() != null)
            mark.setExamidfk(examRepository.findById(dto.getExamidfk()).orElseThrow());
        if (dto.getStudentidfk() != null)
            mark.setStudentidfk(studentRepository.findById(dto.getStudentidfk()).orElseThrow());
        return MarkDto.toDto(markRepository.save(mark));
    }

    @Override
    public void delete(Long id) {
        markRepository.deleteById(id);
    }
}
