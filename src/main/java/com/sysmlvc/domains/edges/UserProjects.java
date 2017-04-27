package com.sysmlvc.domains.edges;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.sysmlvc.domains.base.Edge;
import com.sysmlvc.domains.Project;
import com.sysmlvc.domains.User;
import com.sysmlvc.domains.User.SecurityRole;
/**
 * Created by Jason Han on 2/5/17.
 */

@RelationshipEntity(type = "USER_PROJECTS")
public class UserProjects extends Edge {

    @StartNode
    private User user;

    @EndNode
    private Project project;

    private SecurityRole role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public SecurityRole getRole() {
        return role;
    }

    public void setRole(SecurityRole role) {
        this.role = role;
    }
}
