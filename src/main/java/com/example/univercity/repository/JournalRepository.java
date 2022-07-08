package com.example.univercity.repository;

import com.example.univercity.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    @Query(value = "select id from journal where group_id=:groupId",nativeQuery = true)
    List<Long> getJournalIdByGroupId(@Param("groupId")Long groupId);
}
