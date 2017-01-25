package org.mwg.plugins.rules.nodes.condition.operator.arithmetic;

import org.mwg.Graph;
import org.mwg.plugins.rules.nodes.condition.operator.BinaryOperator;

import java.util.Arrays;

public class SupOrEqual extends BinaryOperator {
    public static final String NODE_NAME = "SupOrEqual";


    public SupOrEqual(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }

    @Override
    protected boolean applyOp(Object[] values) {
        if(values[0] instanceof Number && values[1] instanceof Number) {
            double dV0 = Double.MIN_VALUE;
            long lV0 = Long.MIN_VALUE;
            boolean dV0IsDle = false;
            if (values[0] instanceof Double) {
                dV0 = ((Number) values[0]).doubleValue();
                dV0IsDle = true;
            } else {
                lV0 = ((Number)values[0]).longValue();
            }
            double dV1 = Double.MIN_VALUE;
            long lV1 = Long.MIN_VALUE;
            boolean dV1IsDle = false;
            if (values[1] instanceof Double) {
                dV1 = ((Number) values[1]).doubleValue();
                dV1IsDle = true;
            } else {
                lV1 = ((Number)values[1]).longValue();
            }

            if (dV0IsDle) {
                if (dV1IsDle) {
                    return dV0 >= dV1;
                } else {
                    return dV0 >= lV1;
                }
            } else {
                if (dV1IsDle) {
                    return lV0 >= dV1;
                } else {
                    return lV0 >= lV1;
                }
            }
        } else {
            throw new RuntimeException(Arrays.toString(values) + " should only contains numerical values");
        }
    }
}
