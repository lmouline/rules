package org.mwg.plugins.rules;

import org.mwg.Graph;
import org.mwg.Node;
import org.mwg.Type;
import org.mwg.base.BaseNode;
import org.mwg.struct.EGraph;
import org.mwg.struct.ENode;
import org.mwg.struct.ERelation;

public class RuleNode extends BaseNode {
    public static final String NODE_NAME = "RuleNode";

    public static final String NAME = "name";
    public static final byte NAME_TYPE = Type.STRING;

    public static final String ACTION = "action";
    public static final byte ACTIOIN_TYPE = Type.STRING;

    public static final String IS_TRIGGERED = "is_trigered";
    public static final byte IS_TRIGGERED_TYPE = Type.BOOL;

    public static final String CONDITION = "condition";
    public static final byte CONDITION_TYPE = Type.EGRAPH;

    public static final String VALUE = "value";
    public static final byte VALUE_TYPE = Type.BOOL;


    public RuleNode(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }

    @Override
    public Object get(String propertyName) {
        if(CONDITION.equals(propertyName)) {
            return super.getOrCreate(CONDITION,Type.EGRAPH);
        } else if(VALUE.equals(propertyName)) {
            EGraph graph = (EGraph) super.getOrCreate(CONDITION,Type.EGRAPH);
            ENode root = graph.root();
            return root != null && eval(root);
        } else {
            return super.get(propertyName);

        }
    }

    @Override
    public Node setAt(int index, byte type, Object value) {
        if(index == this._resolver.stringToHash(CONDITION,false) && type != CONDITION_TYPE) {
            throw new RuntimeException("Wrong type for " + CONDITION + " attribute. Expected: " + CONDITION_TYPE + ". Actual: " + type);
        }

        if(index == this._resolver.stringToHash(VALUE,false)) {
            throw new RuntimeException(VALUE + " is a readonly attribute.");
        }

        if(index == this._resolver.stringToHash(ACTION,false) && type != ACTIOIN_TYPE) {
            throw new RuntimeException("Wrong type for " + ACTION + " attribute. Expected: " + ACTIOIN_TYPE + ". Actual: " + type);
        }

        return super.setAt(index, type, value);
    }

    private static boolean eval(ENode condition) {
        int type = (int) condition.get(RulesConstants.TYPE);

        switch (type) {
            case RulesConstants.TYPE_CONSTANT_BOOL_VALUE:
            {
                return (boolean) condition.get(VALUE);
            }
            case RulesConstants.TYPE_NOT_OPERATOR: {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                return !eval(left.nodes()[0]);
            }
            case RulesConstants.TYPE_AND_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return eval(left.nodes()[0]) && eval(right.nodes()[0]);
            }
            case RulesConstants.TYPE_OR_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return eval(left.nodes()[0]) || eval(right.nodes()[0]);
            }
            case RulesConstants.TYPE_SUPOREQ_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return evalArith(left.nodes()[0]) >= evalArith(right.nodes()[0]);
            }
            case RulesConstants.TYPE_SUP_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return evalArith(left.nodes()[0]) > evalArith(right.nodes()[0]);
            }
            case RulesConstants.TYPE_DIFF_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return evalArith(left.nodes()[0]) != evalArith(right.nodes()[0]);
            }
            case RulesConstants.TYPE_LESSOREQ_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return evalArith(left.nodes()[0]) <= evalArith(right.nodes()[0]);
            }
            case RulesConstants.TYPE_LESS_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return evalArith(left.nodes()[0]) < evalArith(right.nodes()[0]);
            }
            case RulesConstants.TYPE_EQUAL_OPERATOR:
            {
                ERelation left = (ERelation) condition.get(RulesConstants.LEFT_TERM);
                ERelation right = (ERelation) condition.get(RulesConstants.RIGHT_TERM);
                return evalArith(left.nodes()[0]) == evalArith(right.nodes()[0]);
            }
            default:
                throw new RuntimeException("Type with id " + type + " is an unknown boolean type.");

        }
    }

    private static double evalArith(ENode expr) {
        int type = (int) expr.get(RulesConstants.TYPE);

        switch (type) {
            case RulesConstants.TYPE_NODE_VALUE:
                return -1;
            case RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE:
                return (double) expr.get(VALUE);
            default:
                throw new RuntimeException("Type with id " + type + " is an unknown arithmetic type.");
        }
    }
}
