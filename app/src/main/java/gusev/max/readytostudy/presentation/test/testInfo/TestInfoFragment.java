package gusev.max.readytostudy.presentation.test.testInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.test.TestActivity;
import gusev.max.readytostudy.presentation.test.TestActivityCallback;

import static gusev.max.readytostudy.domain.model.TestModel.TEST_MODEL;

/**
 * Created by v on 04/02/2018.
 */

public class TestInfoFragment extends Fragment {

    @BindView(R.id.test_name)
    TextView testName;
    @BindView(R.id.test_description)
    TextView testDescription;
    @BindView(R.id.tasks_count)
    TextView tasksCount;

    private TestModel model;

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TestInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.start_test)
    void onStartTestClick() {
        if (activityCallback != null) {
            activityCallback.onStartPressed();
        }
    }

    TestActivityCallback activityCallback;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            model = (TestModel) getArguments().getSerializable(TEST_MODEL);
        }
        try {
            activityCallback = (TestActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement activityCallback");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_test_info, null);
        unbinder = ButterKnife.bind(this, view);
        setupWidgets();
        return view;
    }

    private void setupWidgets() {
        if (model != null) {
            testName.setText(model.getName());
            testDescription.setText(model.getDescription());
            tasksCount.setText(String.valueOf(model.getTasksCount()));
        }
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
