package com.example.univercity.repository;

import com.example.univercity.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
