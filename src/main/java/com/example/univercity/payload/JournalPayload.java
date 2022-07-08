package com.example.univercity.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JournalPayload {
    String name;
    Long groupId;
    Long subjectId;
}
