package com.example.univercity.repository;

import com.example.univercity.entity.Group;
import com.example.univercity.entity.Student;
import com.example.univercity.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    @Query(value = "select count(*) from student where group_id=:groupId",nativeQuery = true)
    int getStudentCountByGroupId(@Param("groupId")Long groupId);

    @Query(value = "select id from student where group_id=:groupId",nativeQuery = true)
    List<Long> getStudentsIdByGroupId(@Param("groupId")Long groupId);
}
