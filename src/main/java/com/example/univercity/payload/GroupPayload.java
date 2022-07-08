package com.example.univercity.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupPayload {
    String name;
    int year;
    Long facultyId;
    List<Long> subjectsId;
}
