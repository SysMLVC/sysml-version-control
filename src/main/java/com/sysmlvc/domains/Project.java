package com.sysmlvc.domains;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.sysmlvc.domains.base.Node;

import java.util.Set;

/**
 * Created by Jason Han on 1/23/17.
 */

@NodeEntity
public class Project extends Node {

    private User creator;
    private User modifier;

    @Relationship(type = "PROJECT_BRANCHES", direction = Relationship.INCOMING)
    private Set<Branch> branches = new HashSet<>();

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getModifier() {
        return modifier;
    }

    public void setModifier(User modifier) {
        this.modifier = modifier;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }
}
