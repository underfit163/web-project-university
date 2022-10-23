package com.underfit.trpo.service;

import com.underfit.trpo.dao.ExamRepository;
import com.underfit.trpo.dao.SubjectRepository;
import com.underfit.trpo.dao.TeacherRepository;
import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.entities.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements UniversityService<ExamDto> {
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<ExamDto> getAll() {
        return examRepository.findAllExams().orElseThrow()
                .stream()
                .map(ExamDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto getById(Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        return exam != null ? ExamDto.toDto(exam) : null;
    }

    @Override
    public ExamDto save(ExamDto dto) {
        Exam exam = dto.toEntity();
        exam.setTeacheridfk(teacherRepository.findById(dto.getTeacheridfk()).orElseThrow());
        exam.setSubjectidfk(subjectRepository.findById(dto.getSubjectidfk()).orElseThrow());
        return ExamDto.toDto(examRepository.save(exam));
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}
