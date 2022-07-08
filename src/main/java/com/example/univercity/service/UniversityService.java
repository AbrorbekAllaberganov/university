package com.example.univercity.service;

import com.example.univercity.entity.University;
import com.example.univercity.model.Result;
import com.example.univercity.payload.UniversityPayload;

public interface UniversityService {
    Result saveUniversity(UniversityPayload universityPayload);
    Result editUniversity(Long universityId,UniversityPayload universityPayload);
    Result deleteUniversity(Long universityId);
    Result getAll();
    Result findById(Long universityId);
}
