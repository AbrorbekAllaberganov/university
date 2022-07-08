package com.example.univercity.repository;

import com.example.univercity.entity.Group;
import com.example.univercity.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value ="select s.* from subject s\n" +
            "inner join groups_subject gs on s.id=gs.subject_id\n" +
            "inner join student st on st.group_id=gs.groups_id where st.id=:studentId" ,nativeQuery = true)
    List<Subject>getSubjectsByStudentId(@Param("studentId")Long studentId);
}
