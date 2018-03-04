package gusev.max.readytostudy.presentation.test.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import gusev.max.readytostudy.domain.model.TasksModel;
import gusev.max.readytostudy.presentation.viewholder.AnswerViewHolder;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by v on 28/02/2018.
 */

public class AnswersListAdapter extends RecyclerView.Adapter<AnswerViewHolder> implements
        AnswerViewHolder.TaskClickListener {

    private final LayoutInflater inflater;
    private TasksModel tasksModel;
    private PublishSubject<TasksModel> clickSubject = PublishSubject.create();

    public AnswersListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AnswerViewHolder.create(inflater, parent, this);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        holder.bind(tasksModel.getCurrentTask().getVars().get(position));
    }

    @Override
    public int getItemCount() {
        if (tasksModel != null) {
            return tasksModel.getCurrentTask().getVars() == null ? 0 : tasksModel.getCurrentTask()
                    .getVars().size();

        } else {
            return 0;
        }
    }

    @Override
    public void onAnswerClicked(String nameOfVariant) {
        clickSubject.onNext(TasksModel.addAnswer(tasksModel, nameOfVariant));
    }

    public Observable<TasksModel> getSelectedItemObservable() {
        return clickSubject;
    }

    public void setData(TasksModel tasksModel) {
        this.tasksModel = tasksModel;
        notifyDataSetChanged();
    }
}
