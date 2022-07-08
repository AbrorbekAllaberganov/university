package com.example.univercity.controller;

import com.example.univercity.entity.Mark;
import com.example.univercity.model.Result;
import com.example.univercity.payload.MarkPayload;
import com.example.univercity.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/mark")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @PostMapping("/")
    public ResponseEntity<Result> saveMark(@RequestBody MarkPayload markPayload){
        Result result=markService.saveMark(markPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editMark(@PathVariable("id")Long id,
                                                 @RequestBody MarkPayload markPayload){
        Result result=markService.editMark(id,markPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllMark(){
        Result result=markService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findMarkById(@PathVariable("id")Long id){
        Result result=markService.findMarkById(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteMarkById(@PathVariable("id")Long id){
        Result result=markService.deleteMark(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }





}
