package com.example.univercity.entity;

import com.example.univercity.entity.helper.AbstractEntityUUID;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Faculty extends AbstractEntityUUID {

    @Column(nullable = false)
    String name;

    @ManyToOne
    University university;

}
