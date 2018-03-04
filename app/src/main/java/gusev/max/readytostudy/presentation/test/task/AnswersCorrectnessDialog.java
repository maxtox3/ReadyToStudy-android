package gusev.max.readytostudy.presentation.test.task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.TasksModel;

import static gusev.max.readytostudy.domain.model.TasksModel.TASKS_MODEL;

/**
 * Created by v on 02/03/2018.
 */

public class AnswersCorrectnessDialog extends AppCompatDialogFragment {

    public static final String TAG = AnswersCorrectnessDialog.class.getName();

    @BindView(R.id.answer_correctness_container)
    LinearLayout container;
    @BindView(R.id.question_text)
    TextView question;
    @BindView(R.id.right_answer_text)
    TextView right;
    @BindView(R.id.proceed_button)
    Button proceed;

    private TasksModel tasksModel;
    private ProceedAnswerClickListener listener;

    public interface ProceedAnswerClickListener {
        void onProceedClicked(TasksModel tasksModel);
    }

    public static Fragment newInstance(TasksModel tasksModel) {
        AnswersCorrectnessDialog f = new AnswersCorrectnessDialog();
        Bundle args = new Bundle();
        args.putSerializable(TASKS_MODEL, tasksModel);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            listener = (ProceedAnswerClickListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment()
                    .toString() + " must implement ProceedAnswerClickListener");
        }
        setRetainInstance(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            tasksModel = (TasksModel) arguments.getSerializable(TASKS_MODEL);
        } else if (savedInstanceState != null) {
            tasksModel = (TasksModel) savedInstanceState.getSerializable(TASKS_MODEL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(
                R.layout.dialog_fragment_correctness_of_answer,
                container,
                true
        );
        ButterKnife.bind(this, view);

        setupWidgets();
        setupBackground();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TASKS_MODEL, tasksModel);
    }

    private void setupWidgets() {
        right.setText(tasksModel.getPassedTask().getRightAnswer());
        question.setText(tasksModel.getPassedTask().getQuestion());
        proceed.setOnClickListener(view -> {
            if (listener != null) {
                listener.onProceedClicked(tasksModel);
            }
        });
    }

    private void setupBackground() {
        if (tasksModel.getPassedTask().getGivenAnswer()
                .equals(tasksModel.getPassedTask().getRightAnswer())) {
            question.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            right.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            container.setBackgroundColor(getResources().getColor(R.color.good_answer_color));
        } else {
            question.setTextColor(getResources().getColor(R.color.white));
            right.setTextColor(getResources().getColor(R.color.white));
            container.setBackgroundColor(getResources().getColor(R.color.bad_answer_color));
        }
    }
}
