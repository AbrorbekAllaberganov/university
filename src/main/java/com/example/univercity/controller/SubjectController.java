package com.example.univercity.controller;

import com.example.univercity.entity.Subject;
import com.example.univercity.model.Result;
import com.example.univercity.payload.SubjectPayload;
import com.example.univercity.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/")
    public ResponseEntity<Result> saveSubject(@RequestBody SubjectPayload subjectPayload){
        Result result=subjectService.saveSubject(subjectPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editSubject(@PathVariable("id")Long id,
                                                 @RequestBody SubjectPayload subjectPayload){
        Result result=subjectService.editSubject(id,subjectPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllSubject(){
        Result result=subjectService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findSubjectById(@PathVariable("id")Long id){
        Result result=subjectService.findSubjectById(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteSubjectById(@PathVariable("id")Long id){
        Result result=subjectService.deleteSubject(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }





}
