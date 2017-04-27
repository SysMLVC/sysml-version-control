package com.sysmlvc.domains.nodes;

import com.sysmlvc.domains.base.Node;

import java.util.Set;

/**
 * Created by Jason Han on 12/15/16.
 */

public class Organization extends Node {

    private Set<Role> roles;
    private Set<Project> projects;
    private User creator;
    private User modifier;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

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
}
