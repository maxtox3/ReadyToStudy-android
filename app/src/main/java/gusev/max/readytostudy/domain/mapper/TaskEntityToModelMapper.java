package gusev.max.readytostudy.domain.mapper;

import java.util.LinkedList;

import gusev.max.readytostudy.data.entity.TaskEntity;
import gusev.max.readytostudy.domain.model.TaskModel;
import io.reactivex.functions.Function;

/**
 * Created by v on 27/02/2018.
 */

public class TaskEntityToModelMapper implements Function<TaskEntity, TaskModel> {

    @Override
    public TaskModel apply(TaskEntity taskEntity) throws Exception {
        LinkedList<String> vars = new LinkedList<>();
        vars.add(taskEntity.getFirstVar());
        vars.add(taskEntity.getSecondVar());
        vars.add(taskEntity.getThirdVar());
        vars.add(taskEntity.getFourthVar());
        return new TaskModel(
            taskEntity.getId(),
            taskEntity.getName(),
            taskEntity.getTestId(),
            vars,
            taskEntity.getRightVar());
    }
}
