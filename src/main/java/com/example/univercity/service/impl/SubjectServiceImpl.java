package com.example.univercity.service.impl;

import com.example.univercity.entity.Group;
import com.example.univercity.entity.Subject;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.SubjectPayload;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.repository.StudentRepository;
import com.example.univercity.repository.SubjectRepository;
import com.example.univercity.service.SubjectService;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final Result result;

    @Override
    public Result getSubjectByStudentId(Long studentId) {
        try {
            if (studentRepository.existsById(studentId))
                return result.success(subjectRepository.getSubjectsByStudentId(studentId));
            throw new ResourceNotFoundException(studentId + " student id not found");
        }catch (Exception e){
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result saveSubject(SubjectPayload subjectPayload) {
        try {
            Subject subject = new Subject();
            subject.setName(subjectPayload.getName());
            subjectRepository.save(subject);
            return result.success(subject);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.success(e);
        }
    }

    @Override
    public Result editSubject(Long subjectId, SubjectPayload subjectPayload) {
        try {
            Subject subject =subjectRepository.findById(subjectId)
                            .orElseThrow(()->new ResourceNotFoundException(subjectId+" subject id not found"));
            subject.setName(subjectPayload.getName());
            subjectRepository.save(subject);
            return result.success(subject);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteSubject(Long subjectId) {
        try {
            List<Group> groupList = groupRepository.getGroupsBySubjectId(subjectId);
            groupList.forEach(group -> {
                List<Subject> subjectList = group.getSubject();
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getId().equals(subjectId))
                        subjectList.remove(i);
                }
                group.setSubject(subjectList);
            });
            groupRepository.saveAll(groupList);
            subjectRepository.deleteById(subjectId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result findSubjectById(Long subjectId) {
        try {
            return result.success(subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new ResourceNotFoundException(subjectId + " id not found")));
        }catch (Exception e){
            log.error(e.getMessage());
            return result.error(e);
        }

    }

    @Override
    public Result getAll() {
        return result.success(subjectRepository.findAll(Sort.by("createdAt")));
    }
}
