package org.mwg.plugins.rules;

import greycat.*;
import greycat.struct.EGraph;
import greycat.struct.ENode;
import greycat.struct.ERelation;
import greycat.struct.Relation;
import org.junit.Assert;
import org.junit.Test;


public class RulesTest {

    @Test
    public void testRules() {
        Graph graph = new GraphBuilder().withPlugin(new RulesPlugin()).build();
        graph.connect(new Callback<Boolean>() {
            @Override
            public void on(Boolean result) {
                RuleNode ruleNode = (RuleNode) graph.newTypedNode(0,0,RuleNode.NODE_NAME);
                EGraph conditionGraph = (EGraph) ruleNode.get(RuleNode.CONDITION);

                // constant == constant1
                ENode constant = conditionGraph.newNode();
                constant.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE);
                constant.set(RulesConstants.VALUE, Type.DOUBLE,55.);

                ENode constant1 = conditionGraph.newNode();
                constant1.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE);
                constant1.set(RulesConstants.VALUE, Type.DOUBLE,56.);

                ENode eqNode = conditionGraph.newNode();
                eqNode.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_EQUAL_OPERATOR);

                ERelation left = (ERelation) eqNode.getOrCreate(RulesConstants.LEFT_TERM, Type.ERELATION);
                left.add(constant);
                eqNode.set(RulesConstants.LEFT_TERM,Type.ERELATION,left);
                ERelation right = (ERelation) eqNode.getOrCreate(RulesConstants.RIGHT_TERM,Type.ERELATION);
                right.add(constant1);
                eqNode.set(RulesConstants.RIGHT_TERM,Type.ERELATION,right);

                // constant2 >= constant3
                ENode constant2 = conditionGraph.newNode();
                constant2.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE);
                constant2.set(RulesConstants.VALUE, Type.DOUBLE,89.);

                ENode constant3 = conditionGraph.newNode();
                constant3.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE);
                constant3.set(RulesConstants.VALUE, Type.DOUBLE,114.);

                ENode supEqNode = conditionGraph.newNode();
                supEqNode.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_SUPOREQ_OPERATOR);

                ERelation left2 = (ERelation) supEqNode.getOrCreate(RulesConstants.LEFT_TERM,Type.ERELATION);
                left2.add(constant2);
                supEqNode.set(RulesConstants.LEFT_TERM,Type.ERELATION,left2);
                ERelation right2 = (ERelation) supEqNode.getOrCreate(RulesConstants.RIGHT_TERM,Type.ERELATION);
                right2.add(constant3);
                supEqNode.set(RulesConstants.RIGHT_TERM,Type.ERELATION,right2);

                // (constant == constant1) && (constant2 >= constant3)
                ENode and = conditionGraph.newNode();
                and.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_AND_OPERATOR);

                ERelation left3= (ERelation) and.getOrCreate(RulesConstants.LEFT_TERM,Type.ERELATION);
                left3.add(supEqNode);
                and.set(RulesConstants.LEFT_TERM,Type.ERELATION,left3);
                ERelation right3 = (ERelation) and.getOrCreate(RulesConstants.RIGHT_TERM,Type.ERELATION);
                right3.add(eqNode);
                and.set(RulesConstants.RIGHT_TERM,Type.ERELATION,right3);

                // not ((constant == constant1) && (constant2 >= constant3))
                ENode not = conditionGraph.newNode();
                not.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_NOT_OPERATOR);

                ERelation left4= (ERelation) not.getOrCreate(RulesConstants.LEFT_TERM,Type.ERELATION);
                left4.add(and);
                not.set(RulesConstants.LEFT_TERM,Type.ERELATION,left4);

                conditionGraph.setRoot(not);

                Assert.assertTrue(((boolean)ruleNode.get(RuleNode.VALUE)));

                graph.disconnect(null);
            }
        });
    }

    @Test
    public void testRulesWithNV() {
        Graph graph = new GraphBuilder().withPlugin(new RulesPlugin()).build();
        graph.connect(new Callback<Boolean>() {
            @Override
            public void on(Boolean result) {
                Node node = graph.newNode(0,0);
                node.set("value",Type.INT,57);

                RuleNode ruleNode = (RuleNode) graph.newTypedNode(0,0,RuleNode.NODE_NAME);
                EGraph condition = (EGraph) ruleNode.get(RuleNode.CONDITION);

                ENode constant = condition.newNode();
                constant.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE);
                constant.set(RulesConstants.VALUE,Type.DOUBLE,56.);

                ENode nodeValue = condition.newNode();
                nodeValue.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_NODE_VALUE);
                Relation relation = (Relation) constant.getOrCreate(RulesConstants.NODE_VALUE_RELATION,Type.RELATION);
                relation.addNode(node);
                nodeValue.set(RulesConstants.NODE_VALUE_RELATION,Type.RELATION,relation);
                nodeValue.set(RulesConstants.NODE_VALUE_ATTRIBUTE,RulesConstants.NODE_VALUE_ATTRIBUTE_TYPE,"value");

                // nodeValue.relation.attribute > constant
                ENode moreThan = condition.newNode();
                moreThan.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_SUP_OPERATOR);
                ERelation left = (ERelation) moreThan.getOrCreate(RulesConstants.LEFT_TERM,Type.ERELATION);
                left.add(nodeValue);
                moreThan.set(RulesConstants.LEFT_TERM, Type.ERELATION,left);
                ERelation right = (ERelation) moreThan.getOrCreate(RulesConstants.RIGHT_TERM,Type.ERELATION);
                right.add(constant);
                moreThan.set(RulesConstants.RIGHT_TERM,Type.ERELATION,right);

                condition.setRoot(moreThan);

                Assert.assertTrue((Boolean) ruleNode.get(RuleNode.VALUE));
            }
        });
    }

    @Test
    public void testRulesWithParams() {
        Graph graph = new GraphBuilder().withPlugin(new RulesPlugin()).build();
        graph.connect(new Callback<Boolean>() {
            @Override
            public void on(Boolean result) {
                RuleNode ruleNode = (RuleNode) graph.newTypedNode(0,0,RuleNode.NODE_NAME);
                EGraph condition = (EGraph) ruleNode.get(RuleNode.CONDITION);

                ENode constant = condition.newNode();
                constant.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_CONSTANT_ARITHMETIC_VALUE);
                constant.set(RulesConstants.VALUE,Type.DOUBLE,56.);

                ENode nodeVar = condition.newNode();
                nodeVar.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_VARIABLE);
                nodeVar.set(RulesConstants.PARAMETRIC_INDEX,RulesConstants.PARAMETRIC_INDEX_TYPE,0);

                // nodeValue.relation.attribute > constant
                ENode moreThan = condition.newNode();
                moreThan.set(RulesConstants.TYPE,RulesConstants.TYPE_TYPE,RulesConstants.TYPE_SUP_OPERATOR);
                ERelation left = (ERelation) moreThan.getOrCreate(RulesConstants.LEFT_TERM,Type.ERELATION);
                left.add(nodeVar);
                moreThan.set(RulesConstants.LEFT_TERM, Type.ERELATION,left);
                ERelation right = (ERelation) moreThan.getOrCreate(RulesConstants.RIGHT_TERM,Type.ERELATION);
                right.add(constant);
                moreThan.set(RulesConstants.RIGHT_TERM,Type.ERELATION,right);

                condition.setRoot(moreThan);

                Assert.assertTrue((Boolean) ruleNode.getWithParams(RuleNode.VALUE,87));
            }
        });
    }

    @Test
    public void test() {
        Graph graph = new GraphBuilder().withPlugin(new RulesPlugin()).build();
        graph.connect(new Callback<Boolean>() {
            @Override
            public void on(Boolean result) {
                Tasks.newTask()
                        .travelInTime("0")
                        .travelInTime("0")
                        .createNode()
                        .setAttribute("name",Type.STRING,"root1")
                        .setAsVar("test")
                        .log("{{test}}")
                        .thenDo(new ActionFunction() {
                            @Override
                            public void eval(TaskContext ctx) {
                                Node node = (Node) ctx.variable("test").get(0);
                                node.set("attribute",Type.INT,5);
//                                ctx.graph().save(new Callback<Boolean>() {
//                                    @Override
//                                    public void on(Boolean result) {
                                        ctx.continueTask();
//                                    }
//                                });
                            }
                        })
                        .log("{{test}}")
                        .execute(graph, new Callback<TaskResult>() {
                            @Override
                            public void on(TaskResult result) {
                                System.out.println(result.output());
                            }
                        });
            }
        });
    }
}
