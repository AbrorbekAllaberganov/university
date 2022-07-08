package com.example.univercity.service.impl;

import com.example.univercity.entity.Faculty;
import com.example.univercity.entity.Group;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.FacultyPayload;
import com.example.univercity.repository.FacultyRepository;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.repository.StudentRepository;
import com.example.univercity.repository.UniversityRepository;
import com.example.univercity.response.FacultyResponse;
import com.example.univercity.response.GroupResponse;
import com.example.univercity.service.FacultyService;

import com.example.univercity.service.GroupService;
import com.example.univercity.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class FacultyServiceImpl implements FacultyService {
    private final GroupService groupService;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final Result result;

    @Override
    public Result saveFaculty(FacultyPayload facultyPayload) {
        try {
            Faculty faculty = new Faculty();
            faculty.setName(facultyPayload.getName());
            faculty.setUniversity(universityRepository.findById(facultyPayload.getUniversityId())
                    .orElseThrow(() -> new ResourceNotFoundException(facultyPayload.getUniversityId() + " university id not found")));
            facultyRepository.save(faculty);
            return result.success(faculty);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result editFaculty(Long facultyId, FacultyPayload facultyPayload) {
        try {
            Faculty faculty = facultyRepository.findById(facultyId)
                    .orElseThrow(() -> new ResourceNotFoundException(facultyId + " faculty not found"));
            faculty.setName(facultyPayload.getName());
            faculty.setUniversity(universityRepository.findById(facultyPayload.getUniversityId())
                    .orElseThrow(() -> new ResourceNotFoundException(facultyPayload.getUniversityId() + " university id not found")));
            facultyRepository.save(faculty);
            return result.success(faculty);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteFaculty(Long facultyId) {
        try {
            List<Long> groupsId = groupRepository.getGroupsIdByFacultyId(facultyId);
            groupsId.forEach(groupService::deleteGroup);

            facultyRepository.deleteById(facultyId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result findFacultyById(Long facultyId) {
        try {
            Faculty faculty = facultyRepository.findById(facultyId)
                    .orElseThrow(() -> new ResourceNotFoundException(facultyId + " not found"));

            List<Group> groupList = groupRepository.getGroupsByFacultyId(facultyId);

            List<GroupResponse> groupResponses = groupList.stream()
                    .map(group -> new GroupResponse(group.getId(), group.getName(), studentRepository.getStudentCountByGroupId(group.getId())))
                    .collect(Collectors.toList());


            FacultyResponse facultyResponse = new FacultyResponse(facultyId, faculty.getName(), groupResponses);
            return result.success(facultyResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getAll() {
        return result.success(facultyRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public List<Long> getFacultyIdByUniversityId(Long universityId) {
        return facultyRepository.getFacultyIdByUniversityId(universityId);
    }
}
