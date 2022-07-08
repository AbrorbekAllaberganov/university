package com.example.univercity.repository;

import com.example.univercity.entity.Group;
import com.example.univercity.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value = "select * from groups g inner join groups_subject gs \n" +
            "on g.id=gs.groups_id where gs.subject_id=:subjectId",nativeQuery = true)
    List<Group> getGroupsBySubjectId(@Param("subjectId") Long subjectId);

    @Query(value = "select * from groups where faculty_id=:facultyId",nativeQuery = true)
    List<Group> getGroupsByFacultyId(@Param("facultyId")Long facultyId);

    @Query(value = "select id from groups where faculty_id=:facultyId",nativeQuery = true)
    List<Long> getGroupsIdByFacultyId(@Param("facultyId")Long facultyId);

}
