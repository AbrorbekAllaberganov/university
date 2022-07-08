package com.example.univercity.service;

import com.example.univercity.entity.Subject;
import com.example.univercity.model.Result;
import com.example.univercity.payload.SubjectPayload;



public interface SubjectService {
    Result saveSubject(SubjectPayload subjectPayload);
    Result editSubject(Long subjectId,SubjectPayload subjectPayload);
    Result deleteSubject(Long subjectId);
    Result findSubjectById(Long subjectId);
    Result getAll();
    Result getSubjectByStudentId(Long studentId);
}
