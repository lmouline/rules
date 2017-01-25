package org.mwg.plugins.rules.nodes.condition.operator;

import org.mwg.Callback;
import org.mwg.DeferCounterSync;
import org.mwg.Graph;
import org.mwg.Node;
import org.mwg.internal.utility.CoreDeferCounterSync;
import org.mwg.plugins.rules.nodes.condition.Term;

public class NotOperator extends Term {
    public static final String NODE_NAME = "NotOperator";

    public static final String CHILD = "child";
    public NotOperator(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }

    @Override
    public Object get(String propertyName) {
        DeferCounterSync defer = new CoreDeferCounterSync(1);
        relation(CHILD, new Callback<Node[]>() {
            @Override
            public void on(Node[] result) {
                if(result.length < 0) {
                    defer.wrap().on(false);
                } else {
                    Object val = result[0].get(VALUE);
                    defer.wrap().on(val.equals(true));
                }

                defer.count();

            }
        });
        return !((Boolean) defer.waitResult());

    }
}
