package org.mwg.plugins.rules;


import greycat.Graph;
import greycat.Type;
import greycat.base.BaseNode;

public class PullEventNode extends BaseNode {
    public static final String NODE_NAME = "PullEventNode";

    public static final String NAME = "name";
    public static final byte NAME_TYPE = Type.STRING;

    public static final String EVENTS = "events";

    public PullEventNode(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }
}
