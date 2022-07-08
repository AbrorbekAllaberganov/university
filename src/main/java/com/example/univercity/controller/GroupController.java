package com.example.univercity.controller;

import com.example.univercity.entity.Group;
import com.example.univercity.model.Result;
import com.example.univercity.payload.GroupPayload;
import com.example.univercity.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/")
    public ResponseEntity<Result> saveGroup(@RequestBody GroupPayload groupPayload){
        Result result=groupService.saveGroup(groupPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editGroup(@PathVariable("id")Long id,
                                                 @RequestBody GroupPayload groupPayload){
        Result result=groupService.editGroup(id,groupPayload);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/")
    public ResponseEntity<Result> getAllGroup(){
        Result result=groupService.getAll();
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findGroupById(@PathVariable("id")Long id){
        Result result=groupService.findGroupById(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteGroupById(@PathVariable("id")Long id){
        Result result=groupService.deleteGroup(id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }





}
