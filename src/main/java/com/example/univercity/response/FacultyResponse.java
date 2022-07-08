package com.example.univercity.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacultyResponse {
    Long facultyId;
    String facultyName;
    List<GroupResponse> groupResponse;
}
