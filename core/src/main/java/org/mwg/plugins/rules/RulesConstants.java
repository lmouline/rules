package org.mwg.plugins.rules;

import org.mwg.Type;

public class RulesConstants {

    /**
     * Value attribute returned by the ENode when it is evaluated.
     * The value is either stored or computed.
     */
    public static final String VALUE = "value";

    /**
     * Store the ENode type has an attribute
     */
    public static final String TYPE = "type";
    public static final byte TYPE_TYPE = Type.INT;


    /**
     * Value := {@link #NODE_VALUE_RELATION}.{@link #NODE_VALUE_ATTRIBUTE}
     */
    public static final int TYPE_NODE_VALUE = 0;

    /**
     * Value directly stored and it is an arithmetic
     */
    public static final int TYPE_CONSTANT_ARITHMETIC_VALUE = 1;

    /**
     * Value directly stored and it is an boolean
     */
    public static final int TYPE_CONSTANT_BOOL_VALUE = 11;

    /**
     * Value := !{@link #LEFT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_NOT_OPERATOR = 2;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} && {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_AND_OPERATOR = 3;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} || {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_OR_OPERATOR = 4;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} >= {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_SUPOREQ_OPERATOR = 5;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} > {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_SUP_OPERATOR = 6;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} != {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_DIFF_OPERATOR = 7;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} <= {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_LESSOREQ_OPERATOR = 8;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} < {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_LESS_OPERATOR = 9;

    /**
     * Value:= {@link #LEFT_TERM}.{@link #VALUE} == {@link #RIGHT_TERM}.{@link #VALUE}
     */
    public static final int TYPE_EQUAL_OPERATOR = 10;



    /**
     * Node value
     */
    public static final String NODE_VALUE_RELATION = "node";
    public static final String NODE_VALUE_ATTRIBUTE = "attribute";
    public static final byte NODE_VALUE_ATTRIBUTE_TYPE = Type.STRING;


    /**
     * Left term
     */
    public static final String LEFT_TERM = "left";

    /**
     * Right term
     */
    public static final String RIGHT_TERM = "right";
}
