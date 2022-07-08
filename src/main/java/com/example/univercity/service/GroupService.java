package com.example.univercity.service;


import com.example.univercity.entity.Group;
import com.example.univercity.model.Result;
import com.example.univercity.payload.GroupPayload;

import java.util.List;


public interface GroupService {
    Result saveGroup(GroupPayload groupPayload);

    Result editGroup(Long groupId, GroupPayload groupPayload);

    Result deleteGroup(Long groupId);

    Result findGroupById(Long groupId);

    Result getAll();

}
