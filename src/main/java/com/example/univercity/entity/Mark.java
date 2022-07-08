package com.example.univercity.entity;

import com.example.univercity.entity.helper.AbstractEntityUUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mark extends AbstractEntityUUID {

    @Column(nullable = false)
    int mark;

    @OneToOne
    Student student;

    @ManyToOne
    Journal journal;

}
