package com.example.univercity.controller;

import com.example.univercity.entity.Student;
import com.example.univercity.model.Result;
import com.example.univercity.payload.StudentPayload;
import com.example.univercity.service.StudentService;
import com.example.univercity.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;

    @PostMapping("/")
    public ResponseEntity<Result> saveStudent(@RequestBody StudentPayload studentPayload){
        Result result=studentService.saveStudent(studentPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editStudent(@PathVariable("id")Long id,
                                                 @RequestBody StudentPayload studentPayload){
        Result result=studentService.editStudent(id,studentPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllStudent(){
        Result result=studentService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findStudentById(@PathVariable("id")Long id){
        Result result=studentService.findStudentById(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteStudentById(@PathVariable("id")Long id){
        Result result=studentService.deleteStudent(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/subject")
    public ResponseEntity<Result> getStudentSubjects(@RequestParam("studentId")Long studentId){
        Result result=subjectService.getSubjectByStudentId(studentId);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Result> findStudentByName(@PathVariable("name")String name){
        Result result=studentService.findStudentByName(name);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @GetMapping("/mark")
    public ResponseEntity<?> getStudentsMark(@RequestParam("groupId")Long groupId,
                                             @RequestParam("subjectId")Long subjectId){
        Result result=studentService.getStudentsWithMark(groupId,subjectId);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }



}
