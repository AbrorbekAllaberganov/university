package com.example.univercity.repository;

import com.example.univercity.entity.Journal;
import com.example.univercity.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query(value = "select * from mark\n" +
            "where journal_id=(select id from journal where subject_id=:subjectId and group_id=:groupId)\n" +
            "order by mark desc",nativeQuery = true)
    List<Mark> getAllStudentWithMark(@Param("groupId")Long groupId,@Param("subjectId")Long subjectId);

    @Query(value = "select id from mark where journal_id=:journalId",nativeQuery = true)
    List<Long> getMarksIdByJournalId(@Param("journalId")Long journalId);
}
