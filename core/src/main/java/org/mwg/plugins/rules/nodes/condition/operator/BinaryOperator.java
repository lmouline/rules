package org.mwg.plugins.rules.nodes.condition.operator;

import org.mwg.Callback;
import org.mwg.DeferCounter;
import org.mwg.Graph;
import org.mwg.Node;
import org.mwg.internal.utility.CoreDeferCounter;
import org.mwg.plugins.rules.nodes.condition.Term;

public abstract class BinaryOperator extends Term {
    public static final String LEFT = "left";
    public static final String RIGHT = "right";


    public BinaryOperator(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }

    @Override
    public Object get(String propertyName) {
        if(propertyName.equals(VALUE)) {
            final Object[] values = new Object[2];
            DeferCounter defer = new CoreDeferCounter(2);
            relation(LEFT, new Callback<Node[]>() {
                @Override
                public void on(Node[] result) {
                    if(result.length != 1) {
                        values[0] = null;
                    } else {
                        values[0] = result[0].get(VALUE);
                    }
                    defer.count();
                }
            });

            relation(RIGHT, new Callback<Node[]>() {
                @Override
                public void on(Node[] result) {
                    if(result.length != 1) {
                        values[1] = null;
                    } else {
                        values[1] = result[0].get(VALUE);
                    }
                    defer.count();
                }
            });

            return applyOp(values);
        }
        return super.get(propertyName);
    }

    protected abstract boolean applyOp(Object[] values);


}
