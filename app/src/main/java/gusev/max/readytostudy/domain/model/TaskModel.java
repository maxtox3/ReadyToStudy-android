package gusev.max.readytostudy.domain.model;

import java.util.LinkedList;

import gusev.max.readytostudy.presentation.base.BaseModel;

/**
 * Created by v on 04/02/2018.
 */

public class TaskModel extends BaseModel {

    public static final String TASK_MODEl = TaskModel.class.getName();

    private final Long testId;
    private final LinkedList<String> vars;
    private final Integer rightVar;

    public TaskModel(
        Long id, String name, Long testId, LinkedList<String> vars, Integer rightVar) {
        super(id, name);
        this.testId = testId;
        this.vars = vars;
        this.rightVar = rightVar;
    }

    public Long getTestId() {
        return testId;
    }

    public LinkedList<String> getVars() {
        return vars;
    }

    public Integer getRightVar() {
        return rightVar;
    }
}
