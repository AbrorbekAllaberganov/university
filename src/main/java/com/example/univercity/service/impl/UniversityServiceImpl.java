package com.example.univercity.service.impl;

import com.example.univercity.entity.University;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.UniversityPayload;
import com.example.univercity.repository.UniversityRepository;
import com.example.univercity.service.FacultyService;
import com.example.univercity.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final FacultyService facultyService;
    private final Result result;

    @Override
    public Result saveUniversity(UniversityPayload universityPayload) {
        try {
            University university = new University();
            university.setName(universityPayload.getName());
            university.setAddress(universityPayload.getAddress());
            university.setOpenYear(universityPayload.getYear());

            universityRepository.save(university);
            return result.success(university);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result editUniversity(Long universityId, UniversityPayload universityPayload) {
        try {
            University university = universityRepository.findById(universityId)
                            .orElseThrow(()->new ResourceNotFoundException(universityId+" university id not found"));
            university.setName(universityPayload.getName());
            university.setAddress(universityPayload.getAddress());
            university.setOpenYear(universityPayload.getYear());

            universityRepository.save(university);

            return result.success(university);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteUniversity(Long universityId) {
        try {
            List<Long>facultyId=facultyService.getFacultyIdByUniversityId(universityId);
            facultyId.forEach(facultyService::deleteFaculty);

//            universityRepository.deleteById(universityId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getAll() {
        return result.success(universityRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public Result findById(Long universityId) {
        try {
            return result.success(universityRepository.findById(universityId)
                    .orElseThrow(() -> new ResourceNotFoundException(universityId + " not found")));
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }

    }


}
