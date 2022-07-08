package com.example.univercity.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupResponse {
    Long groupId;
    String groupName;
    int studentsCount;
}
