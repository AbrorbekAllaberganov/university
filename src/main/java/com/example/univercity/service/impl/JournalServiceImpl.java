package com.example.univercity.service.impl;

import com.example.univercity.entity.Journal;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.JournalPayload;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.repository.JournalRepository;
import com.example.univercity.repository.SubjectRepository;
import com.example.univercity.service.JournalService;
import com.example.univercity.service.MarkService;
import com.example.univercity.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final MarkService markService;
    private final Result result;

    @Override
    public Result saveJournal(JournalPayload journalPayload) {
        try {
            Journal journal = new Journal();
            journal.setName(journalPayload.getName());
            journal.setGroup(groupRepository.findById(journalPayload.getGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException(journalPayload.getGroupId() + " group id not found")));
            journal.setSubject(subjectRepository.findById(journalPayload.getSubjectId())
                    .orElseThrow(() -> new ResourceNotFoundException(journalPayload.getSubjectId() + " subject not found")));
            journalRepository.save(journal);
            return result.success(journal);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result editJournal(Long journalId, JournalPayload journalPayload) {
        try {
            Journal journal = journalRepository.findById(journalId)
                    .orElseThrow(() -> new ResourceNotFoundException(journalId + " journal id not found"));
            journal.setName(journalPayload.getName());
            journal.setGroup(groupRepository.findById(journalPayload.getGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException(journalPayload.getGroupId() + " group id not found")));
            journal.setSubject(subjectRepository.findById(journalPayload.getSubjectId())
                    .orElseThrow(() -> new ResourceNotFoundException(journalPayload.getSubjectId() + " subject not found")));
            journalRepository.save(journal);
            return result.success(journal);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteJournal(Long journalId) {
        try {
            List<Long> marksId = markService.getMarksIdByJournalId(journalId);
            marksId.forEach(markService::deleteMark);
            journalRepository.deleteById(journalId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getAll() {
        return result.success(journalRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public Result findJournalById(Long journalId) {
        try {
            return result.success(journalRepository.findById(journalId)
                    .orElseThrow(() -> new ResourceNotFoundException(journalId + " journal id not fund")));
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public List<Long> getJournalIdByGroupId(Long groupId) {
        return journalRepository.getJournalIdByGroupId(groupId);
    }
}
