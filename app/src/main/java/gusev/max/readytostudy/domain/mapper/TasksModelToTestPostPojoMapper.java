package gusev.max.readytostudy.domain.mapper;

import gusev.max.readytostudy.data.pojo.TestPostPojo;
import gusev.max.readytostudy.domain.model.TasksModel;
import io.reactivex.functions.Function;

/**
 * Created by v on 06/03/2018.
 */

public class TasksModelToTestPostPojoMapper implements Function<TasksModel, TestPostPojo> {

    @Override
    public TestPostPojo apply(TasksModel tasksModel) {

        return new TestPostPojo(
                tasksModel.getTestModel().getId(),
                tasksModel.getPassedOkTasksIds().size(),
                tasksModel.getPassedBadTasksIds().size(),
                System.currentTimeMillis() - tasksModel.getStart()
        );
    }
}
