package gusev.max.readytostudy.domain.model;

import java.util.List;

/**
 * Created by v on 22/02/2018.
 */

public class TasksModel {

    private final TestModel testModel;
    private final List<TaskModel> tasks;
    private final List<TaskModel> passedOk;
    private final List<TaskModel> passedBad;
    private final PassedTaskModel passedTask;

    public TasksModel(
            TestModel testModel,
            List<TaskModel> tasks,
            List<TaskModel> passedOk,
            List<TaskModel> passedBad,
            PassedTaskModel passedTask
    ) {
        this.testModel = testModel;
        this.tasks = tasks;
        this.passedOk = passedOk;
        this.passedBad = passedBad;
        this.passedTask = passedTask;
    }

    public TestModel getTestModel() {
        return testModel;
    }

    public List<TaskModel> getPassedOk() {
        return passedOk;
    }

    public List<TaskModel> getPassedBad() {
        return passedBad;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public int getCountOfPassedAnswers() {
        return getPassedOk().size() + getPassedBad().size();
    }

    public int getCountOfAnswers() {
        return tasks.size();
    }

    public PassedTaskModel getPassedTask() {
        return passedTask;
    }
}
