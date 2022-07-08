package com.example.univercity.service;

import com.example.univercity.entity.Faculty;
import com.example.univercity.model.Result;
import com.example.univercity.payload.FacultyPayload;
import com.example.univercity.response.FacultyResponse;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface FacultyService {
    Result saveFaculty(FacultyPayload facultyPayload);
    Result editFaculty(Long facultyId,FacultyPayload facultyPayload);
    Result deleteFaculty(Long facultyId);
    Result findFacultyById(Long facultyId);
    Result getAll();
    List<Long> getFacultyIdByUniversityId(Long universityId);

}
