package com.sysmlvc.domains;

import com.sysmlvc.domains.base.Node;

/**
 * Created by Jason Han on 1/31/17.
 */

public class Branch extends Node {

    private User creator;
    private User modifier;

    private boolean tag;

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
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
