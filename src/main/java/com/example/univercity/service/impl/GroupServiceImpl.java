package com.example.univercity.service.impl;

import com.example.univercity.entity.Group;
import com.example.univercity.entity.Subject;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.GroupPayload;
import com.example.univercity.repository.FacultyRepository;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.repository.SubjectRepository;
import com.example.univercity.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final JournalService journalService;
    private final SubjectRepository subjectRepository;
    private final StudentService studentService;
    private final Result result;

    @Override
    public Result saveGroup(GroupPayload groupPayload) {
        try {
            Group group = new Group();
            group.setName(groupPayload.getName());
            group.setFaculty(facultyRepository.findById(groupPayload.getFacultyId())
                    .orElseThrow(() -> new ResourceNotFoundException(groupPayload.getFacultyId() + " faculty id not found")));
            group.setSubject(findSubject(groupPayload.getSubjectsId()));
            groupRepository.save(group);
            return result.success(group);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result editGroup(Long groupId, GroupPayload groupPayload) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException(groupId + " not found"));
            group.setName(groupPayload.getName());
            group.setFaculty(facultyRepository.findById(groupPayload.getFacultyId())
                    .orElseThrow(() -> new ResourceNotFoundException(groupPayload.getFacultyId() + " faculty id not found")));
            group.setSubject(findSubject(groupPayload.getSubjectsId()));
            groupRepository.save(group);
            return result.success(group);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteGroup(Long groupId) {
        try {
            List<Long> journalsId = journalService.getJournalIdByGroupId(groupId);
            journalsId.forEach(journalService::deleteJournal);

            List<Long> studentsId = studentService.getStudentsIdByGroupId(groupId);
            studentsId.forEach(studentService::deleteStudent);

            groupRepository.deleteById(groupId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result findGroupById(Long groupId) {
        try {
            return result.success(groupRepository.findById(groupId)
                    .orElseThrow(() -> new ResourceNotFoundException(groupId + " id not found")));
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getAll() {
        return result.success(groupRepository.findAll(Sort.by("createdAt")));
    }


    public List<Subject> findSubject(List<Long> subjectsId) {
        List<Subject> subjectList = new ArrayList<>();
        subjectsId.forEach(id -> subjectList.add(subjectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" subject id not found"))));
        return subjectList;
    }


}
