package com.example.univercity.service;

import com.example.univercity.entity.Student;
import com.example.univercity.model.Result;
import com.example.univercity.payload.StudentPayload;

import java.sql.ResultSet;
import java.util.List;


public interface StudentService {
    Result saveStudent(StudentPayload studentPayload);
    Result editStudent(Long studentId,StudentPayload studentPayload);
    Result deleteStudent(Long studentId);
    Result findStudentById(Long studentId);
    Result findStudentByName(String studentName);
    Result getStudentsWithMark(Long groupId,Long subjectId);
    Result getAll();
    List<Long> getStudentsIdByGroupId(Long groupId);
}
