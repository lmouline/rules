package org.mwg.plugins.rules;

import org.mwg.Graph;
import org.mwg.Type;
import org.mwg.plugin.NodeFactory;
import org.mwg.plugin.Plugin;
import org.mwg.plugins.rules.nodes.condition.ConstantValue;
import org.mwg.plugins.rules.nodes.condition.NodeValue;
import org.mwg.plugins.rules.nodes.condition.PullEventNode;
import org.mwg.plugins.rules.nodes.condition.operator.NotOperator;
import org.mwg.plugins.rules.nodes.condition.operator.arithmetic.*;
import org.mwg.plugins.rules.nodes.condition.operator.bool.AndOperator;
import org.mwg.plugins.rules.nodes.condition.operator.bool.OrOperator;

public class RulesPlugin implements Plugin {

    @Override
    public void start(Graph graph) {
        graph.nodeRegistry()
                .declaration(RuleNode.NODE_NAME)
                .setFactory((world, time, id, g) -> {
                    RuleNode ruleNode = new RuleNode(world,time,id, g);
//                    ruleNode.set(RuleNode.RESOLUTIONTASK,RuleNode.RESOLUTIONTASK_TYPE,RuleNode.TASK);
                    ruleNode.set("attribute", Type.INT,66);
                    return ruleNode;
                });

        graph.nodeRegistry()
                .declaration(PullEventNode.NODE_NAME)
                .setFactory((NodeFactory) (world, time, id, g) -> new PullEventNode(world,time,id, g));

        graph.nodeRegistry()
                .declaration(NodeValue.NODE_NAME)
                .setFactory((NodeFactory) (world, time, id, g) -> new NodeValue(world,time,id, g));

        graph.nodeRegistry()
                .declaration(ConstantValue.NODE_NAME)
                .setFactory((NodeFactory) (world, time, id, g) -> new ConstantValue(world,time,id, g));

        graph.nodeRegistry()
                .declaration(NotOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new NotOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(OrOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new OrOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(AndOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new AndOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(EqualOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new EqualOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(LessOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new LessOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(LessOrEqualOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new LessOrEqualOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(NotEqualOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new NotEqualOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(SupOperator.NODE_NAME)
                .setFactory((world, time, id, g) -> new SupOperator(world,time,id,g));

        graph.nodeRegistry()
                .declaration(SupOrEqual.NODE_NAME)
                .setFactory((world, time, id, g) -> new SupOrEqual(world,time,id,g));
    }

    @Override
    public void stop() {

    }
}
