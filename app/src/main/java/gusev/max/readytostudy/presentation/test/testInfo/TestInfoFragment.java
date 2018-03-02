package gusev.max.readytostudy.presentation.test.testInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.test.TestActivity;
import gusev.max.readytostudy.presentation.test.TestActivityCallback;

/**
 * Created by v on 04/02/2018.
 */

public class TestInfoFragment extends Fragment {

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
        @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_info, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
