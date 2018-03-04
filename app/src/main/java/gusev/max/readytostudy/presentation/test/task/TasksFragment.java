package gusev.max.readytostudy.presentation.test.task;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvi.MviFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.test.TestActivityCallback;
import gusev.max.readytostudy.utils.StringService;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static gusev.max.readytostudy.domain.model.TestModel.TEST_MODEL;
import static gusev.max.readytostudy.utils.Constants.DIALOG_REQUEST_CODE;

/**
 * Created by v on 04/02/2018.
 */

public class TasksFragment extends MviFragment<TasksView, TasksPresenter> implements TasksView,
        AnswersCorrectnessDialog.ProceedAnswerClickListener {

    @BindView(R.id.answers_recycler)
    RecyclerView answers;
    @BindView(R.id.count_of_passed_answers)
    TextView countOfAnswers;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.error_view)
    TextView errorView;
    @BindView(R.id.task_question)
    TextView taskQuestion;

    private Unbinder unbinder;
    private TestActivityCallback activityCallback;
    private AnswersListAdapter adapter;
    private Fragment dialog;
    private PublishSubject<TasksModel> subject = PublishSubject.create();


    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCallback = (TestActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement activityCallback");
        }
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupWidgets();

        return view;
    }

    private void setupWidgets() {
        adapter = new AnswersListAdapter(LayoutInflater.from(getActivity()));
        answers.setAdapter(adapter);
        answers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @NonNull
    @Override
    public TasksPresenter createPresenter() {
        return App.getDependencyInjection(getContext()).newTaskPresenter();
    }

    @Override
    public Observable<TestModel> getData() {
        return getTestModel();
    }

    @Override
    public Observable<TasksModel> onAnswerClicked() {
        return adapter.getSelectedItemObservable();
    }

    @Override
    public Observable<TasksModel> getNextTask() {
        return subject;
    }


    @Override
    public void onProceedClicked(TasksModel model) {
        subject.onNext(model);
    }

    @Override
    public void render(BaseViewState state) {
        if (state instanceof TasksViewState.LoadingState) {
            renderLoading();
        } else if (state instanceof TasksViewState.DataState) {
            renderData((TasksModel) ((BaseViewState.DataState) state).getViewObject());
            hideDialog();
            showContent();
        } else if (state instanceof TasksViewState.ErrorState) {
            renderError(((TasksViewState.ErrorState) state).getError());
        } else if (state instanceof TasksViewState.DialogState) {
            renderDialog(((TasksViewState.DialogState) state).getViewObject());
        } else if (state instanceof TasksViewState.FinishState) {
            renderFinish(((TasksViewState.FinishState) state).getViewObject());
        }
    }

    private void renderData(TasksModel viewObject) {
        countOfAnswers.setText(StringService.buildCountOfAnswers(viewObject));
        taskQuestion.setText(viewObject.getCurrentTask().getName());
        adapter.setData(viewObject);
    }

    private void showContent() {
        content.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    private void renderLoading() {
        content.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void renderError(Throwable error) {
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(error.getMessage());
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
    }

    private void renderDialog(TasksModel viewObject) {
        dialog = AnswersCorrectnessDialog.newInstance(viewObject);
        dialog.setTargetFragment(this, DIALOG_REQUEST_CODE);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(dialog, AnswersCorrectnessDialog.TAG)
                .commitAllowingStateLoss();
    }

    private void hideDialog() {
        if (dialog != null && dialog.isVisible()) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .remove(dialog)
                    .commitAllowingStateLoss();
        }
    }

    private void renderFinish(TasksModel viewObject) {
        hideDialog();
        activityCallback.navigateToTestResult(viewObject);
    }

    private Observable<TestModel> getTestModel() {
        if (getArguments() != null) {
            if (getArguments().getSerializable(TEST_MODEL) != null) {
                return Observable.just((TestModel) getArguments().getSerializable(TEST_MODEL));
            }
        }
        return Observable.empty();
    }
}
