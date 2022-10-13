package com.underfit.trpo.controller;

import com.underfit.trpo.dto.MarkDto;
import com.underfit.trpo.service.MarkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/marks")
public class MarkController {
    private final MarkServiceImpl markService;

    @GetMapping
    public List<MarkDto> getMarks() {
        return markService.getAll();
    }

    @GetMapping("/{id}")
    public MarkDto getMark(@PathVariable Long id) {
        return markService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MarkDto create(@RequestBody MarkDto markDto) {
        if (markDto.getId() != null) {
            markDto.setId(null);
        }
        return markService.save(markDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MarkDto update(@RequestParam Long id, @RequestBody MarkDto markDto) {
        markDto.setId(id);
        return markService.save(markDto);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestParam Long id) {
        markService.delete(id);
    }
}
