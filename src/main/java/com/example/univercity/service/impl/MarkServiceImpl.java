package com.example.univercity.service.impl;

import com.example.univercity.entity.Mark;
import com.example.univercity.exceptions.ResourceNotFoundException;
import com.example.univercity.model.Result;
import com.example.univercity.payload.MarkPayload;
import com.example.univercity.repository.JournalRepository;
import com.example.univercity.repository.MarkRepository;
import com.example.univercity.repository.StudentRepository;
import com.example.univercity.service.JournalService;
import com.example.univercity.service.MarkService;

import com.example.univercity.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final JournalRepository journalRepository;
    private final Result result;

    @Override
    public Result saveMark(MarkPayload markPayload) {
        try {
            Mark mark = new Mark();
            mark.setMark(markPayload.getMark());
            mark.setStudent(studentRepository.findById(markPayload.getStudentId())
                    .orElseThrow(()->new ResourceNotFoundException(markPayload.getStudentId()+" student id not found")));
            mark.setJournal(journalRepository.findById(markPayload.getJournalId())
                    .orElseThrow(() -> new ResourceNotFoundException(markPayload.getJournalId() + " journal id not found")));
            markRepository.save(mark);
            return result.success(mark);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result editMark(Long markId, MarkPayload markPayload) {
        try {
            Mark mark = markRepository.findById(markId)
                            .orElseThrow(()->new ResourceNotFoundException(markId+" mark id not found"));
            mark.setMark(markPayload.getMark());
            mark.setStudent(studentRepository.findById(markPayload.getStudentId())
                    .orElseThrow(()->new ResourceNotFoundException(markPayload.getStudentId()+" student id not found")));
            mark.setJournal(journalRepository.findById(markPayload.getJournalId())
                    .orElseThrow(() -> new ResourceNotFoundException(markPayload.getJournalId() + " journal id not found")));
            markRepository.save(mark);
            return result.success(mark);
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result deleteMark(Long markId) {
        try {
            markRepository.deleteById(markId);
            return result.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public Result getAll() {
        return result.success(markRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public Result findMarkById(Long markId) {
        try {
            return result.success(markRepository.findById(markId)
                    .orElseThrow(() -> new ResourceNotFoundException(markId + " mark id not found")));
        }catch (Exception e){
            log.error(e.getMessage());
            return result.error(e);
        }
    }

    @Override
    public List<Long> getMarksIdByJournalId(Long journalId) {
        return markRepository.getMarksIdByJournalId(journalId);
    }
}
