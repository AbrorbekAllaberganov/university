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
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class University extends AbstractEntityUUID {

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String address;
    @Column(name = "open_year",nullable = false)
    int openYear;
}
