package gusev.max.readytostudy.presentation.test.testresult;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.presentation.test.TestActivity;
import gusev.max.readytostudy.presentation.test.TestActivityCallback;
import gusev.max.readytostudy.utils.StringService;

import static gusev.max.readytostudy.domain.model.TasksModel.TASKS_MODEL;

/**
 * Created by v on 04/03/2018.
 */

public class TestResultFragment extends Fragment {

    @BindView(R.id.content_container)
    LinearLayout container;
    @BindView(R.id.test_name)
    TextView name;
    @BindView(R.id.like)
    AppCompatImageView like;
    @BindView(R.id.star_first)
    AppCompatImageView starFirst;
    @BindView(R.id.star_second)
    AppCompatImageView starSecond;
    @BindView(R.id.star_third)
    AppCompatImageView starThird;
    @BindView(R.id.answer_count_text)
    TextView count;
    @BindView(R.id.buttons_container)
    LinearLayout buttonsContainer;

    private TasksModel model;
    private boolean isAnimationShown = false;

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TestResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.try_again)
    void onTryAgainClick() {
        if (activityCallback != null) {
            activityCallback.navigateToTask();
        }
    }

    @OnClick(R.id.finish)
    void onFinishClick() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    TestActivityCallback activityCallback;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            model = (TasksModel) getArguments().getSerializable(TASKS_MODEL);
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
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupWidgets();
        return view;
    }

    private void setupWidgets() {
        if (model != null) {
            name.setText(model.getTestModel().getName());
            count.setText(StringService.buildCountOfPassedAnswersSlashAllAnswers(model));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isAnimationShown) {
            long duration = 200;
            long delay = 200;

            YoYo.with(Techniques.FadeIn)
                    .duration(duration)
                    .playOn(like);

            YoYo.with(Techniques.ZoomIn)
                    .duration(duration)
                    .delay(delay)
                    .playOn(starFirst);

            delay += 200;

            YoYo.with(Techniques.ZoomIn)
                    .duration(duration)
                    .delay(delay)
                    .playOn(starSecond);

            delay += 200;

            YoYo.with(Techniques.ZoomIn)
                    .duration(duration)
                    .delay(delay)
                    .playOn(starThird);

            isAnimationShown = true;
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
