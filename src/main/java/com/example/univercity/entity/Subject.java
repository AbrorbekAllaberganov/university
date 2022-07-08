package com.example.univercity.entity;

import com.example.univercity.entity.helper.AbstractEntityUUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject extends AbstractEntityUUID {

    @Column(nullable = false,unique = true)
    String name;

}
