package com.example.univercity.controller;

import com.example.univercity.entity.Faculty;
import com.example.univercity.model.Result;
import com.example.univercity.payload.FacultyPayload;
import com.example.univercity.service.FacultyService;
import com.example.univercity.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @PostMapping("/")
    public ResponseEntity<Result> saveFaculty(@RequestBody FacultyPayload facultyPayload){
        Result result=facultyService.saveFaculty(facultyPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editFaculty(@PathVariable("id")Long id,
                                                 @RequestBody FacultyPayload facultyPayload){
        Result result=facultyService.editFaculty(id,facultyPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllFaculty(){
        Result result=facultyService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findFacultyById(@PathVariable("id")Long id){
        return ResponseEntity.ok(facultyService.findFacultyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteFacultyById(@PathVariable("id")Long id){
        Result result=facultyService.deleteFaculty(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }





}
