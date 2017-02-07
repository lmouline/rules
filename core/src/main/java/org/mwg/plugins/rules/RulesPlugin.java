package org.mwg.plugins.rules;


import greycat.Graph;
import greycat.plugin.Plugin;

public class RulesPlugin implements Plugin {

    @Override
    public void start(Graph graph) {
        graph.nodeRegistry()
                .declaration(RuleNode.NODE_NAME)
                .setFactory((world, time, id, g) -> new RuleNode(world,time,id, g));
    }

    @Override
    public void stop() {

    }
}
