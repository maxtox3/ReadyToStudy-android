package gusev.max.readytostudy.presentation.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;
import gusev.max.readytostudy.presentation.test.task.TasksFragment;
import gusev.max.readytostudy.presentation.test.testInfo.TestInfoFragment;
import gusev.max.readytostudy.presentation.test.testresult.TestResultFragment;

import static gusev.max.readytostudy.domain.model.TasksModel.TASKS_MODEL;
import static gusev.max.readytostudy.domain.model.TestModel.TEST_MODEL;
import static gusev.max.readytostudy.utils.Constants.TASK_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.TEST_INFO_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.TEST_RESULT_FRAGMENT;

/**
 * Created by v on 04/02/2018.
 */

public class TestActivity extends BaseActivityFragmentContainer implements TestActivityCallback {

    private TestModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        if (savedInstanceState == null) {
            if (getIntent() != null) {
                model = (TestModel) getIntent().getSerializableExtra(TEST_MODEL);
            }
            navigateToTestInfo();
        }
    }

    @Override
    public void setContainerId() {
        setContainerId(R.id.fragment_container);
    }

    @Override
    protected Fragment createFragment(String tag, Bundle args) {
        switch (tag) {
            case TEST_INFO_FRAGMENT:
                return TestInfoFragment.newInstance(args);
            case TASK_FRAGMENT:
                return TasksFragment.newInstance(args);
            case TEST_RESULT_FRAGMENT:
                return TestResultFragment.newInstance(args);
            default:
                Log.i("createFragment: ", "you must add your fragment");
        }
        return null;
    }

    @Override
    public void navigateToTestInfo() {
        navigateToFragment(TEST_INFO_FRAGMENT, getBundle(), false);
    }

    @Override
    public void navigateToTask() {
        navigateToFragment(TASK_FRAGMENT, getBundle(), false);
    }

    @Override
    public void navigateToTestResult(TasksModel finishedTask) {
        Bundle args = new Bundle();
        args.putSerializable(TASKS_MODEL, finishedTask);
        navigateToFragment(TEST_RESULT_FRAGMENT, args, false);
    }

    @Override
    public void onStartPressed() {
        navigateToTask();
    }

    private Bundle getBundle() {
        Bundle args = new Bundle();
        args.putSerializable(TEST_MODEL, model);
        return args;
    }
}
