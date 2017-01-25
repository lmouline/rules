package org.mwg.plugins.rules;

import org.junit.Test;
import org.mwg.Callback;
import org.mwg.Graph;
import org.mwg.GraphBuilder;

public class RulesTest {

    @Test
    public void testRules() {
        Graph graph = new GraphBuilder().withPlugin(new RulesPlugin()).build();
        graph.connect(new Callback<Boolean>() {
            @Override
            public void on(Boolean result) {
//                Node n1 = graph.newNode(0,0);
//                n1.set("attribute", Type.INT,77);
//
//
//                String script = Tasks.newTask().setAttribute("attribute2",Type.INT,"78").toString();
//
//                TaskResult task = Tasks.newTask()
//                        .travelInWorld("0")
//                        .travelInTime("0")
//                        .inject(n1)
//                        .script(script)
//                        .print("{{result}}")
//                        .executeSync(graph);
//
//                System.out.println(task.output());

                RuleNode ruleNode = (RuleNode) graph.newTypedNode(0,0,RuleNode.NODE_NAME);
                System.out.println(ruleNode.get(RuleNode.VALUE));

//                String t = Tasks.newTask().script("ctx.continueWith(ctx.wrap(false));").toString();
//                Task tt = Tasks.newTask().script(t);
//                Task tt = Tasks.newTask().parse(t,graph);
//
//                System.out.println(tt.executeSync(graph));



            }
        });
    }
}
