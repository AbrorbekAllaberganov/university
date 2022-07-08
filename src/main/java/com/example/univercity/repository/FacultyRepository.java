package com.example.univercity.repository;

import com.example.univercity.entity.Faculty;
import com.example.univercity.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value = "select id from faculty where university_id=:universityId", nativeQuery = true)
    List<Long> getFacultyIdByUniversityId(@Param("universityId") Long universityId);
}
