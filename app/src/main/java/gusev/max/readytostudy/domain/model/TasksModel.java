package gusev.max.readytostudy.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by v on 22/02/2018.
 */

public class TasksModel implements Serializable {

    public static final String TAG = TasksModel.class.getName();

    private final TestModel testModel;
    private final List<TaskModel> tasks;
    private final List<Long> passedOkTasksIds;
    private final List<Long> passedBadTasksIds;
    private final TaskModel currentTask;
    private final PassedTaskModel passedTask;

    public TasksModel(
            TestModel testModel,
            List<TaskModel> tasks,
            List<Long> passedOkTasksIds,
            List<Long> passedBadTasksIds,
            TaskModel currentTask,
            PassedTaskModel passedTask
    ) {
        this.testModel = testModel;
        this.tasks = tasks;
        this.passedOkTasksIds = passedOkTasksIds;
        this.passedBadTasksIds = passedBadTasksIds;
        this.currentTask = currentTask;
        this.passedTask = passedTask;
    }

    public TestModel getTestModel() {
        return testModel;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public List<Long> getPassedOkTasksIds() {
        return passedOkTasksIds;
    }

    public List<Long> getPassedBadTasksIds() {
        return passedBadTasksIds;
    }

    public TaskModel getCurrentTask() {
        return currentTask;
    }

    public PassedTaskModel getPassedTask() {
        return passedTask;
    }

    public int getCountOfPassedTasks() {
        return passedOkTasksIds.size();
    }

    public int getCountOfTasks() {
        return tasks.size();
    }

    public static TasksModel addAnswer(TasksModel from, String answer) {
        TaskModel currentTask = from.getCurrentTask();
        String rightVar = currentTask.getVars().get(currentTask.getRightVar() - 1);
        List<Long> notPassed = from.getPassedBadTasksIds();
        List<Long> passed = from.getPassedOkTasksIds();
        PassedTaskModel passedTaskModel = null;
        boolean pass = false;

        if (answer != null) {
            if (rightVar.equals(answer)) {
                pass = true;
            }
            passedTaskModel = new PassedTaskModel(currentTask.getName(), answer, rightVar);
        }

        if (pass) {
            passed.add(currentTask.getId());
        } else {
            notPassed.add(currentTask.getId());
        }

        return new TasksModel(
                from.getTestModel(),
                from.getTasks(),
                passed,
                notPassed,
                currentTask,
                passedTaskModel
        );
    }

    public static TasksModel onNextTask(TasksModel tasksModel) {
        int indexOfCurrent = tasksModel.getTasks().indexOf(tasksModel.getCurrentTask());
        if (indexOfCurrent != tasksModel.getTasks().size() - 1) {
            TaskModel nextTask = tasksModel.getTasks().get(indexOfCurrent + 1);
            return new TasksModel(
                    tasksModel.getTestModel(),
                    tasksModel.getTasks(),
                    tasksModel.getPassedOkTasksIds(),
                    tasksModel.getPassedBadTasksIds(),
                    nextTask,
                    tasksModel.getPassedTask()
            );
        }
        return null;
    }
}
