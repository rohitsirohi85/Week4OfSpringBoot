package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.entities.PostEntity;

import jakarta.persistence.EntityManagerFactory;

@RestController
@RequestMapping(path = "/audit")

// this audit controller made bcz we want to make an api which can show all
// changes for a particular filed which admin want
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory; // to create session for AuditReaderFactory

    @GetMapping(path = "/posts/{postId}") // we will directly use postEntity here instead of Dto bcz this api is for
                                          // admin and admin can see everything right..so no need to use dto for safety
    List<PostEntity> getPostRevisions(@PathVariable Long postId) { // so for getRevisions For a particular post we need
                                                                   // to read all revisions so we can use direct sql
                                                                   // query or best way that we can use auditReader to
                                                                   // read those revisions for admin
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);
        return revisions
                .stream()
                .map(revisionNumber -> reader.find(PostEntity.class, postId, revisionNumber))
                .collect(Collectors.toList());
    }

}
