package org.mwg.plugins.rules;

import org.mwg.Graph;
import org.mwg.Type;
import org.mwg.base.BaseNode;
import org.mwg.plugins.rules.nodes.condition.Term;
import org.mwg.task.TaskResult;
import org.mwg.task.Tasks;

public class RuleNode extends BaseNode {
    public static final String NODE_NAME = "RuleNode";

    public static final String NAME = "name";
    public static final byte NAME_TYPE = Type.STRING;

    public static final String ACTION = "action";
    public static final byte ACTIOIN_TYPE = Type.STRING;

    public static final String IS_TRIGGERED = "is_trigered";
    public static final byte IS_TRIGGERED_TYPE = Type.BOOL;

    public static final String CONDITION = "condition";

    public static final String VALUE = "value";
    public static final byte VALUE_TYPE = Type.BOOL;

    public static final String RESOLUTIONTASK = "resolutionTask";
    public static final byte RESOLUTIONTASK_TYPE = Type.STRING;

    public static final String TASK = Tasks
            .newTask()
            .traverse(CONDITION)
            .ifThenElseScript("ctx.result.size==0",
                    Tasks.newTask().script("ctx.continueWith(ctx.wrap(false));"),
                    Tasks.newTask().attribute(Term.VALUE)
            )
            .toString();

    public RuleNode(long p_world, long p_time, long p_id, Graph p_graph) {
        super(p_world, p_time, p_id, p_graph);
    }

    @Override
    public Object get(String propertyName) {
        if(propertyName.equals(VALUE)) {
//            DeferCounterSync defer = new CoreDeferCounterSync(1);
//            relation(CONDITION, new Callback<Node[]>() {
//                @Override
//                public void on(Node[] result) {
//                    if(result.length == 0) {
//                        defer.wrap().on(false);
//                    } else {
//                        defer.wrap().on(result[0].get(Term.VALUE));
//                    }
//
//                    defer.count();
//                }
//            });
//            return defer.waitResult();
//            String resolutionTask = (String) super.get(RESOLUTIONTASK);
            String resolutionTask = super.getOrCreate();
            TaskResult result = Tasks.newTask()
                    .travelInTime(time() + "")
                    .travelInWorld(world() + "")
                    .inject(this)
                    .inject(false)
                    .parse(resolutionTask,graph())
                    .executeSync(graph());


            boolean res = (boolean) result.get(0);
            return res;


        } else {
            return super.get(propertyName);
        }
    }


}
