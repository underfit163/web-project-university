package com.underfit.trpo.integrationtest;

import com.underfit.trpo.controller.SubjectController;
import com.underfit.trpo.dto.SubjectDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private SubjectController subjectController;

    @WithMockUser(roles = "ADMIN")
    @Transactional
    @Test
    public void testExamTest () {
        List<SubjectDto> subjects = subjectController.getSubjects();
        Assertions.assertEquals(0, subjects.size());
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectname("Java");
        subjectController.create(subjectDto);
        subjectDto = new SubjectDto();
        subjectDto.setSubjectname("Sql");
        subjectController.create(subjectDto);
        subjects = subjectController.getSubjects();
        Assertions.assertEquals(2, subjects.size());
        SubjectDto subject = subjectController.getSubject(1L);
        Assertions.assertEquals("Java", subject.getSubjectname());
        subject.setSubjectname("Math");
        subjectController.update(1L, subject);
        subject = subjectController.getSubject(1L);
        Assertions.assertEquals("Math", subject.getSubjectname());
    }
}
