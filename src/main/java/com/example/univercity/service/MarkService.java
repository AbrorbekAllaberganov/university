package com.example.univercity.service;

import com.example.univercity.entity.Mark;
import com.example.univercity.model.Result;
import com.example.univercity.payload.MarkPayload;

import java.util.List;

public interface MarkService {
    Result saveMark(MarkPayload markPayload);
    Result editMark(Long markId,MarkPayload markPayload);
    Result deleteMark(Long markId);
    Result getAll();
    Result findMarkById(Long markId);
    List<Long> getMarksIdByJournalId(Long journalId);
}
