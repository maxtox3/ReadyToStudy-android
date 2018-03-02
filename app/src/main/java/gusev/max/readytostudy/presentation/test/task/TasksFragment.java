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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gusev.max.readytostudy.App;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.PassedTaskModel;
import gusev.max.readytostudy.domain.model.TaskModel;
import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.domain.model.TestModel;
import gusev.max.readytostudy.presentation.base.BaseViewState;
import gusev.max.readytostudy.presentation.test.TestActivityCallback;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static gusev.max.readytostudy.domain.model.TestModel.TEST_MODEL;

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

    private Unbinder unbinder;
    private TestModel test;
    private TestActivityCallback activityCallback;
    private AnswersListAdapter adapter;
    private TaskModel currentTask;
    private TasksModel tasksModel;
    private Fragment dialog;
    private PublishSubject<TasksModel> subject = PublishSubject.create();


    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            test = (TestModel) getArguments().getSerializable(TEST_MODEL);
        }
        try {
            activityCallback = (TestActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement activityCallback");
        }
        super.onAttach(context);
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
        return Observable.just(test);
    }

    @Override
    public Observable<TasksModel> onAnswerClicked() {
        return adapter
                .getSelectedItemObservable()
                .flatMap(answer -> Observable.just(getViewObject(answer)));
    }

    public Observable<TasksModel> getNextTask() {
        return subject;
    }

    @Override
    public void render(BaseViewState state) {
        if (state instanceof TasksViewState.LoadingState) {
            renderLoading();
        } else if (state instanceof TasksViewState.DataState) {
            renderData((TasksModel) ((BaseViewState.DataState) state).getViewObject());
        } else if (state instanceof TasksViewState.ErrorState) {
            renderError(((TasksViewState.ErrorState) state).getError());
        }
    }

    private void renderData(TasksModel viewObject) {
        if (viewObject.getPassedTask() != null) {
            renderPassedTask(viewObject.getPassedTask());
        }
        this.tasksModel = viewObject;
        int passed = viewObject.getCountOfPassedAnswers();
        countOfAnswers.setText(passed + "/" + viewObject.getCountOfAnswers());
        currentTask = viewObject.getTasks().get(passed);
        adapter.setData(currentTask.getVars());
        content.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    private void renderPassedTask(PassedTaskModel passedTask) {
        if (dialog == null) {
            dialog = AnswersCorrectnessDialog.newInstance(passedTask);
        }
        if (!dialog.isVisible()) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .show(dialog)
                    .commitAllowingStateLoss();
        }
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

    private TasksModel getViewObject(String answer) {
        boolean pass = false;
        String rightVar = currentTask.getVars().get(currentTask.getRightVar() - 1);
        if (answer != null) {
            if (rightVar.equals(answer)) {
                pass = true;
            }
        }
        List<TaskModel> passed = tasksModel.getPassedOk();
        List<TaskModel> notPassed = tasksModel.getPassedBad();

        if (pass) {
            passed.add(currentTask);
        } else {
            notPassed.add(currentTask);
        }

        return new TasksModel(
                tasksModel.getTestModel(),
                tasksModel.getTasks(),
                passed,
                notPassed,
                tasksModel.getPassedTask()
        );
    }

    @Override
    public void onProceedClicked() {
        subject.onNext(getViewObject(null));
    }
}
