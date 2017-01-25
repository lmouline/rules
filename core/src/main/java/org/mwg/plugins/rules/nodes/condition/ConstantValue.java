package org.mwg.plugins.rules.nodes.condition;

import org.mwg.Graph;

public class ConstantValue extends Term {
    public static final String NODE_NAME = "ConstantValue";

    public ConstantValue(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }
}
