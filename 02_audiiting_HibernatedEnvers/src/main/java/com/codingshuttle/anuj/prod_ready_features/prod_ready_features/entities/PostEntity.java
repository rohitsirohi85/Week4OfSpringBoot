package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.entities;

import org.hibernate.envers.Audited;
// import org.hibernate.envers.NotAudited;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Audited
public class PostEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    // @NotAudited // used when we want that a field or entity not set audited so it
    // can be changed without saving any info(using auditing)
    private String description;

    // there are some methods inside AuditingEntityListener.class which help us to
    // use updatedBy and other methods ..we can use these methods directly like..

    @PrePersist
    void beforeSaved() {

    }

    @PreUpdate
    void beforeUpdate() {

    }

    @PreRemove
    void beforeDelete() {

    }

}
