package gusev.max.readytostudy.presentation.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.base.BaseActivityFragmentContainer;
import gusev.max.readytostudy.presentation.test.task.TasksFragment;
import gusev.max.readytostudy.presentation.test.testInfo.TestInfoFragment;

import static gusev.max.readytostudy.domain.model.TestModel.TEST_MODEL;
import static gusev.max.readytostudy.utils.Constants.TASK_FRAGMENT;
import static gusev.max.readytostudy.utils.Constants.TEST_INFO_FRAGMENT;

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
                return new TestInfoFragment();
            case TASK_FRAGMENT:
                return TasksFragment.newInstance(args);
            default:
                Log.i("createFragment: ", "you must add your fragment");
        }
        return null;
    }

    @Override
    public void navigateToTestInfo() {
        navigateToFragment(TEST_INFO_FRAGMENT, null, false);
    }

    @Override
    public void navigateToTask(TestModel model) {
        Bundle args = new Bundle();
        args.putSerializable(TEST_MODEL, model);
        navigateToFragment(TASK_FRAGMENT, args, true);
    }

    @Override
    public void onStartPressed() {
        navigateToTask(model);
    }
}
