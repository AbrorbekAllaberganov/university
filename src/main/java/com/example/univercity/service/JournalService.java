package com.example.univercity.service;


import com.example.univercity.entity.Journal;
import com.example.univercity.model.Result;
import com.example.univercity.payload.JournalPayload;

import java.util.List;

public interface JournalService  {
    Result saveJournal(JournalPayload journalPayload);
    Result editJournal(Long journalId,JournalPayload journalPayload);
    Result deleteJournal(Long journalId);
    Result getAll();
    Result findJournalById(Long journalId);
    List<Long> getJournalIdByGroupId(Long groupId);
}
