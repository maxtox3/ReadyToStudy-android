package gusev.max.readytostudy.presentation.test.task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import gusev.max.readytostudy.R;
import gusev.max.readytostudy.domain.model.PassedTaskModel;

/**
 * Created by v on 02/03/2018.
 */

public class AnswersCorrectnessDialog extends DialogFragment {

    @BindView(R.id.answer_correctness_container)
    LinearLayout container;
    @BindView(R.id.question_text)
    TextView question;
    @BindView(R.id.answer_given_text)
    TextView given;
    @BindView(R.id.proceed_button)
    Button proceed;

    private PassedTaskModel passedTask;
    private ProceedAnswerClickListener listener;

    public interface ProceedAnswerClickListener {
        void onProceedClicked();
    }

    public static Fragment newInstance(PassedTaskModel passedTaskModel) {
        Fragment f = new AnswersCorrectnessDialog();
        Bundle args = new Bundle();
        args.putSerializable(PassedTaskModel.TAG, (Serializable) passedTaskModel);
        f.setArguments(args);

        return f;
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
            passedTask = (PassedTaskModel) arguments.getSerializable(PassedTaskModel.TAG);
        } else if (savedInstanceState != null) {
            passedTask = (PassedTaskModel) savedInstanceState.getSerializable(PassedTaskModel.TAG);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PassedTaskModel.TAG, passedTask);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.dialog_fragment_correctness_of_answer, container);
        ButterKnife.bind(this, view);

        setupWidgets();

        return view;
    }

    private void setupWidgets() {
        proceed.setOnClickListener(view -> {
            if (listener != null) {
                listener.onProceedClicked();
                dismiss();
            }
        });
    }
}
