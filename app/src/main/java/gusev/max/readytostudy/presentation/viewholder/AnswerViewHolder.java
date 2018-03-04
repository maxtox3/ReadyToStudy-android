package gusev.max.readytostudy.presentation.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gusev.max.readytostudy.R;

/**
 * Created by v on 28/02/2018.
 */

public class AnswerViewHolder extends RecyclerView.ViewHolder {

    public interface TaskClickListener {
        void onAnswerClicked(String nameOfVariant);
    }

    public static AnswerViewHolder create(
            LayoutInflater inflater,
            ViewGroup parent,
            TaskClickListener listener
    ) {
        return new AnswerViewHolder(
                inflater.inflate(R.layout.item_answer, parent, false),
                listener
        );
    }

    @BindView(R.id.answer)
    TextView answer;

    private String textOfAnswer;

    private AnswerViewHolder(View itemView, TaskClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> clickListener.onAnswerClicked(textOfAnswer));
    }

    public void bind(@NonNull String answer) {
        this.textOfAnswer = answer;
        this.answer.setText(this.textOfAnswer);
    }
}
