package gusev.max.readytostudy.presentation.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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

    public static AnswerViewHolder create(LayoutInflater inflater, TaskClickListener listener) {
        return new AnswerViewHolder(inflater.inflate(R.layout.item_answer, null, false), listener);
    }

    @BindView(R.id.answer)
    Button answer;

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
