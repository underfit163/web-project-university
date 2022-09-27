package com.underfit.trpo.service;

import com.underfit.trpo.dao.MarkRepository;
import com.underfit.trpo.dto.MarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements UniversityService<MarkDto> {
    private final MarkRepository markRepository;

    @Override
    public List<MarkDto> getAll() {
        return markRepository
                .findAll()
                .stream()
                .map(MarkDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MarkDto getById(Long id) {
        return MarkDto.toDto(markRepository.findById(id).orElse(null));
    }

    @Override
    public MarkDto save(MarkDto dto) {
        return MarkDto.toDto(markRepository.save(dto.toEntity()));
    }

    @Override
    public void delete(Long id) {
        markRepository.deleteById(id);
    }
}
