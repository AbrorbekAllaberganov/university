package com.example.univercity.controller;

import com.example.univercity.entity.Journal;
import com.example.univercity.model.Result;
import com.example.univercity.payload.JournalPayload;
import com.example.univercity.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;

    @PostMapping("/")
    public ResponseEntity<Result> saveJournal(@RequestBody JournalPayload journalPayload){
        Result result=journalService.saveJournal(journalPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editJournal(@PathVariable("id")Long id,
                                                 @RequestBody JournalPayload journalPayload){
        Result result=journalService.editJournal(id,journalPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllJournal(){
        Result result=journalService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findJournalById(@PathVariable("id")Long id){
        Result result=journalService.findJournalById(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteJournalById(@PathVariable("id")Long id){
        Result result=journalService.deleteJournal(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }





}
