package com.underfit.trpo.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.underfit.trpo.controller.ExamController;
import com.underfit.trpo.dto.ExamDto;
import com.underfit.trpo.service.ExamServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean//создаем объект класса, причем он может быть интерфейсом
    // (Генерирует заглушку, перекрывает все публичные методы класса, будет вызываться метод объекта заглушки,
    // делать ничего не будет)
    private ExamServiceImpl service;
    @Autowired
    private ExamController controller;
    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void getExamsTest() throws Exception {
        List<ExamDto> examDtoList = new ArrayList<>(
                Arrays.asList(new ExamDto(1L, "экзамен", 54, 1, 1L, 1L),
                        new ExamDto(2L, "экзамен", 48, 2, 1L, 1L),
                        new ExamDto(3L, "экзамен", 36, 2, 1L, 1L)));

        when(service.getAll()).thenReturn(examDtoList);
        mockMvc.perform(get("/api/v1/exams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(examDtoList.size()))
                .andDo(print());
    }

    @WithMockUser
    @Test
    public void getExamTest() throws Exception {
        long id = 1L;
        ExamDto examDto = new ExamDto(id, "экзамен", 54, 1, 1L, 1L);

        when(service.getById(id)).thenReturn(examDto);
        mockMvc.perform(get("/api/v1/exams/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.passtype").value(examDto.getPasstype()))
                .andExpect(jsonPath("$.totalhours").value(examDto.getTotalhours()))
                .andExpect(jsonPath("$.semester").value(examDto.getSemester()))
                .andDo(print());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void updateExamTest() throws Exception {
        Long id = 1L;
        ExamDto examDto = new ExamDto(id, "экзамен", 54, 1, 1L, 1L);
        service.save(examDto);
        ExamDto updateExamDto = new ExamDto(id, "зачет", 36, 2, 1L, 1L);
        when(service.save(any(ExamDto.class))).thenReturn(updateExamDto);
        mockMvc.perform(put("/api/v1/exams/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateExamDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passtype").value(updateExamDto.getPasstype()))
                .andExpect(jsonPath("$.totalhours").value(updateExamDto.getTotalhours()))
                .andExpect(jsonPath("$.semester").value(updateExamDto.getSemester()))
                .andDo(print());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void createExamTest() throws Exception {
        ExamDto examDtoResult = new ExamDto(1L, "экзамен", 54, 2, 1L, 1L);
        //если кто то пропрост сохранить объекта класса ExamDto.class, то вернем в качестве ответа объект который мы создали(examDtoResult)
        when(service.save(any(ExamDto.class))).thenReturn(examDtoResult);

        ExamDto examDto = new ExamDto();
        examDto.setPasstype("экзамен");
        examDto.setTotalhours(54);
        examDto.setSemester(2);
        examDto.setSubjectidfk(1L);
        examDto.setTeacheridfk(1L);

        mockMvc.perform(post("/api/v1/exams").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(examDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(examDtoResult.getId()))
                .andDo(print());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void deleteTest() throws Exception {
        long id = 1L;
        doNothing().when(service).delete(id);
        mockMvc.perform(delete("/api/v1/exams/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
