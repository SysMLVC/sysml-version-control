package com.sysmlvc.domains.nodes;

import com.sysmlvc.domains.base.Node;

/**
 * Created by Jason Han on 1/31/17.
 */

public class Branch extends Node {

    private boolean tag;

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }
}
