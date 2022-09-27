package com.underfit.trpo.controller;

import com.underfit.trpo.dto.MarkDto;
import com.underfit.trpo.service.MarkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/marks")
public class MarkController {
    private final MarkServiceImpl markService;

    @GetMapping
    public List<MarkDto> getMarks() {
        return markService.getAll();
    }



}
