package com.example.univercity.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarkResponse {
    Long studentId;
    String studentName;
    int mark;
}
