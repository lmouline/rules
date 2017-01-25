package org.mwg.plugins.rules.nodes.condition;


import org.mwg.*;
import org.mwg.internal.utility.CoreDeferCounterSync;

public class NodeValue extends Term{
    public static final String NODE_NAME = "NodeValue";

    public static final String TARGET = "target";

    public static final String ATTNAME = "attName";
    public static final byte ATTNAME_TYPE = Type.STRING;

    public NodeValue(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }


    @Override
    public Object get(String propertyName) {
        if(propertyName.equals(VALUE)) {
            DeferCounterSync defer = new CoreDeferCounterSync(1);
            relation(TARGET, new Callback<Node[]>() {
                @Override
                public void on(Node[] result) {
                    if(result.length == 0) {
                        defer.wrap().on(null);
                    } else {
                        defer.wrap().on(result[0].get((String) get(ATTNAME)));
                    }
                    defer.count();
                }
            });
            return defer.waitResult();
        }
        return super.get(propertyName);
    }
}
