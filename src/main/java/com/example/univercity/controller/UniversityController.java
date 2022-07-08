package com.example.univercity.controller;

import com.example.univercity.entity.University;
import com.example.univercity.model.Result;
import com.example.univercity.payload.UniversityPayload;
import com.example.univercity.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @PostMapping("/")
    public ResponseEntity<Result> saveUniversity(@RequestBody UniversityPayload universityPayload){
        Result result=universityService.saveUniversity(universityPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editUniversity(@PathVariable("id")Long id,
                                                 @RequestBody UniversityPayload universityPayload){
        Result result=universityService.editUniversity(id,universityPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllUniversity(){
        Result result=universityService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findUniversityById(@PathVariable("id")Long id){
        Result result=universityService.findById(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteUniversityById(@PathVariable("id")Long id){
        Result result=universityService.deleteUniversity(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }





}
