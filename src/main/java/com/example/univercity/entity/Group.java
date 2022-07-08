package com.example.univercity.entity;

import com.example.univercity.entity.helper.AbstractEntityUUID;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group extends AbstractEntityUUID {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int year;

    @ManyToOne
    Faculty faculty;

    @ManyToMany
    List<Subject> subject;

}
