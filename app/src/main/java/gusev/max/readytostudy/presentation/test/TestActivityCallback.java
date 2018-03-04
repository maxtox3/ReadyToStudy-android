package gusev.max.readytostudy.presentation.test;

import gusev.max.readytostudy.domain.model.TasksModel;

/**
 * Created by v on 04/02/2018.
 */

public interface TestActivityCallback {

    void navigateToTestInfo();

    void navigateToTask();

    void onStartPressed();

    void navigateToTestResult(TasksModel finishedTask);
}
