package com.example.univercity.service.impl;

import com.example.univercity.entity.Mark;
import com.example.univercity.entity.Student;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.StudentPayload;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.repository.MarkRepository;
import com.example.univercity.repository.StudentRepository;
import com.example.univercity.repository.SubjectRepository;
import com.example.univercity.response.MarkResponse;
import com.example.univercity.service.GroupService;
import com.example.univercity.service.StudentService;

import com.example.univercity.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final Result result;

    @Override
    public Result saveStudent(StudentPayload studentPayload) {
        try {
            Student student = new Student();
            student.setName(studentPayload.getName());
            student.setGroup(groupRepository.findById(studentPayload.getGroupId())
                    .orElseThrow(()->new ResourceNotFoundException(studentPayload.getGroupId()+" group id not found")));
            studentRepository.save(student);
            return result.success(student);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result editStudent(Long studentId, StudentPayload studentPayload) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId + " not found"));
            student.setName(studentPayload.getName());
            student.setGroup(groupRepository.findById(studentPayload.getGroupId())
                    .orElseThrow(()->new ResourceNotFoundException(studentPayload.getGroupId()+" group id not found")));
            studentRepository.save(student);
            return result.success(student);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteStudent(Long studentId) {
        try {
            studentRepository.deleteById(studentId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getStudentsWithMark(Long groupId, Long subjectId) {
        try {
            if (!groupRepository.existsById(groupId))
                throw new ResourceNotFoundException(groupId+" group id not found");
            if (!subjectRepository.existsById(subjectId))
                throw new ResourceNotFoundException(subjectId+" subject id not found");
            List<Mark> marks = markRepository.getAllStudentWithMark(groupId, subjectId);
            List<MarkResponse> markResponseList = marks.stream()
                    .map(mark -> new MarkResponse(mark.getStudent().getId(), mark.getStudent().getName(), mark.getMark()))
                    .collect(Collectors.toList());

            return result.success(markResponseList);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result findStudentByName(String studentName) {
        return result.success(studentRepository.findByName(studentName));
    }

    @Override
    public Result findStudentById(Long studentId) {
        try {
            return result.success(studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException(studentId + " not found")));
        }catch (Exception e){
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getAll() {
        return result.success(studentRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public List<Long> getStudentsIdByGroupId(Long groupId) {
        return studentRepository.getStudentsIdByGroupId(groupId);
    }
}
