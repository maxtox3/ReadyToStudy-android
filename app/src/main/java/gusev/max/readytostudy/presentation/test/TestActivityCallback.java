package gusev.max.readytostudy.presentation.test;

import gusev.max.readytostudy.domain.model.TestModel;

/**
 * Created by v on 04/02/2018.
 */

public interface TestActivityCallback {

    void navigateToTestInfo();

    void navigateToTask(TestModel model);

    void onStartPressed();
}
