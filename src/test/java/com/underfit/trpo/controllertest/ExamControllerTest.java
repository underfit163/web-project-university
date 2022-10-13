package com.underfit.trpo.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.underfit.trpo.controller.ExamController;
import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.service.ExamServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExamServiceImpl service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void getExamsTest() throws Exception {
        List<ExamDto> examDtoList = new ArrayList<>(
                Arrays.asList(new ExamDto(89L, "экзамен", 54, 1, 89L, 89L),
                        new ExamDto(103L, "экзамен", 54, 2, 89L, 89L),
                        new ExamDto(107L, "экзамен", 54, 2, 89L, 89L)));

        when(service.getAll()).thenReturn(examDtoList);
        mockMvc.perform(get("/api/v1/exams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(examDtoList.size()))
                .andDo(print());
    }
}
