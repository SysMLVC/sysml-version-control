package com.sysmlvc.domains.nodes;

import com.sysmlvc.domains.base.Node;

/**
 * Created by Jason Han on 1/31/17.
 */

public class Role extends Node {

    private User creator;
    private User modifier;

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
