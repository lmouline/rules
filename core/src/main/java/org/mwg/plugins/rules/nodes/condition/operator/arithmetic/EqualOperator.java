package org.mwg.plugins.rules.nodes.condition.operator.arithmetic;

import org.mwg.Graph;
import org.mwg.plugins.rules.nodes.condition.operator.BinaryOperator;

public class EqualOperator extends BinaryOperator {
    public static final String NODE_NAME = "EqualOperator";

    public EqualOperator(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }

    @Override
    protected boolean applyOp(Object[] values) {
        return values[0].equals(values[1]);
    }
}
