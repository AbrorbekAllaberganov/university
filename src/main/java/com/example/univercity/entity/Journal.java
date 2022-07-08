package com.example.univercity.entity;

import com.example.univercity.entity.helper.AbstractEntityUUID;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Journal extends AbstractEntityUUID {
    @Column(nullable = false)
    String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    Group group;

    @ManyToOne
    Subject subject;

}
