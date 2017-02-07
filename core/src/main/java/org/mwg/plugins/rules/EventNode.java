package org.mwg.plugins.rules;


import greycat.Graph;
import greycat.Type;
import greycat.base.BaseNode;

public class EventNode extends BaseNode {
    public static final String NODE_NAME = "EventNode";

    public static final String EVENTTIME = "timeEvent";
    public static final byte EVENTTIME_TYPE = Type.LONG;

    public static final String SRCNODE = "srcNode";

    public EventNode(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }
}
