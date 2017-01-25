package org.mwg.plugins.rules.nodes.condition;


import org.mwg.Graph;
import org.mwg.Type;
import org.mwg.base.BaseNode;

public abstract class Term extends BaseNode {
    public static final String NODE_NAME = "Term";

    public static final String VALUE = "value";
    public static final byte VALUE_TYPE = Type.BOOL;

    public Term(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }
}
