package gusev.max.readytostudy.presentation.test.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gusev.max.readytostudy.presentation.viewholder.AnswerViewHolder;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by v on 28/02/2018.
 */

public class AnswersListAdapter extends RecyclerView.Adapter<AnswerViewHolder> implements AnswerViewHolder.TaskClickListener {

    private final LayoutInflater inflater;
    private List<String> answers;
    private PublishSubject<String> clickSubject = PublishSubject.create();

    public AnswersListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AnswerViewHolder.create(inflater, this);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        holder.bind(answers.get(position));
    }

    @Override
    public int getItemCount() {
        return answers == null ? 0 : answers.size();
    }

    @Override
    public void onAnswerClicked(String nameOfVariant) {
        clickSubject.onNext(nameOfVariant);
    }

    public Observable<String> getSelectedItemObservable() {
        return clickSubject;
    }

    public void setData(List<String> answers) {
        this.answers = answers;
        notifyDataSetChanged();
    }
}
