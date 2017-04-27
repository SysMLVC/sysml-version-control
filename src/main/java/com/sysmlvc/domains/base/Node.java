package com.sysmlvc.domains.base;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

/**
 * Created by Jason Han on 2/3/17.
 */

@NodeEntity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Node {

    @JsonProperty("id")
    private String id;

    private String name;

    @DateLong
    private Date created;

    @DateLong
    private Date modified;

    @DateLong
    private Date deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || id == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return (id == null) ? -1 : id.hashCode();
    }
}
